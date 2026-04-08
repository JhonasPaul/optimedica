package com.optimedica.products.service;

import com.optimedica.common.exception.ResourceNotFoundException;
import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import com.optimedica.products.mapper.ProductMapper;
import com.optimedica.products.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @PostConstruct
    public void init() {
        log.info("🛠 ProductServiceImpl cargado por Spring");
    }



    @Override
    public List<ProductDto> listProduct() {
        var list = productRepository.findAll();
        return productMapper.toListDto(list);
    }

    @Override
    public Page<ProductDto> pageProduct(Pageable pageable) {
        Page<Product> productos = productRepository.findAll(pageable);
        log.info("📦 Total productos encontrados: {}", productos.getTotalElements());
        return productos.map(productMapper::toDto);
//        throw new RuntimeException("🚨 Este servicio se está ejecutando");
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        // Buscamos el producto o lanzamos excepción si no existe
        var producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Producto con el id : " + id + " no fue encontrado"));

        // Convertimos a DTO y lo devolvemos envuelto en Optional
        return Optional.of(productMapper.toDto(producto));
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        var product = productMapper.toProduct(productDto);
        var proSave = productRepository.save(product);
        return productMapper.toDto(proSave);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        var existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Producto con el id : " + id + " no fue encontrado"));

        /*copia todo los valores actuales del dto que viene del cliente hacia la entidad Product*/
        productMapper.updateProductFromDto(productDto, existing); // evita los campos que no se actualizaran
        var updated = productRepository.save(existing);
        return productMapper.toDto(updated);

    }

    //    @Override
    public void deleteProduct(Integer id) {
        var existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Producto con el id : " + id + " no fue encontrado"));

        productRepository.deleteById(existing.getId());
    }
}
