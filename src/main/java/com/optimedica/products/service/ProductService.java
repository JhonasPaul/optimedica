package com.optimedica.products.service;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

public interface ProductService {


    List<ProductDto> listProduct();

    Page<ProductDto> pageProduct(Pageable pageable);

    Optional<ProductDto> findById(Integer id);

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(Integer id, ProductDto productDto);

    void deleteProduct(Integer id);
}
