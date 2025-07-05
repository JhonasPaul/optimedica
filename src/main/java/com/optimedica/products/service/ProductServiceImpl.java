package com.optimedica.products.service;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import com.optimedica.products.mapper.ProductMapper;
import com.optimedica.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDto> listProduct() {
        var list = productRepository.findAll();
        return productMapper.toListDto(list);
    }

    @Override
    public Page<ProductDto> pageProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(u -> productMapper.toDto(u));
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        return productRepository.findById(id)
                .map(p -> productMapper.toDto(p));
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        var product = productMapper.toProduct(productDto);
        var proSave =  productRepository.save(product);
        return productMapper.toDto(proSave);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        var existing = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        existing.setName(productDto.getName());
        existing.setDescription(productDto.getName());
        existing.setPrice(productDto.getPrice());
        existing.setStock(productDto.getStock());
        existing.setBrand(productDto.getBrand());
        existing.setImageURL(productDto.getImageURL());
        existing.setActiveFlag(productDto.getActiveFlag());

        var proSave = productRepository.save(existing);
        return productMapper.toDto(proSave);
    }

//    @Override
    public void deleteProduct(Integer id) {
        var existing = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        productRepository.deleteById(existing.getId());
    }
}
