package com.optimedica.products.dto;
import java.math.BigDecimal;
import java.util.Date;

public record ProductDto(Integer id,
                         String name,
                         String description,
                         BigDecimal price,
                         long stock,
                         String brand,
                         String imageURL,
                         long activeFlag,
                         Date createdAt
                         ) { }
