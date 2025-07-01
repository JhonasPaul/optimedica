package com.optimedica.products.service;

import com.optimedica.products.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProductService {


    List<ProductDto> listarProductDto();

    Page<ProductDto> paginarProducto(Pageable pageable);

    Optional<ProductDto> listrPorId(Integer id);
}
