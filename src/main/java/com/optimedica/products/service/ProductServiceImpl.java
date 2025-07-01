package com.optimedica.products.service;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import com.optimedica.products.mapper.ProductMapper;
import com.optimedica.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<ProductDto> listarProductDto() {
        return productRepository
                .findAll()
                .stream()
                .map(p -> productMapper.toDto(p))
                .toList();
    }

    @Override
    public Page<ProductDto> paginarProducto(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(u -> productMapper.toDto(u));
    }

    @Override
    public Optional<ProductDto> listrPorId(Integer id) {
        return productRepository.findById(id)
                .map(p -> productMapper.toDto(p));
    }
}
