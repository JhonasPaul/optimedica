package com.optimedica.products.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

public record ProductDto( @Schema(description = "ID único del producto", example = "1")
                          Integer id,

                          @Schema(description = "Nombre del producto", example = "Lentes oftálmicos")
                          String name,

                          @Schema(description = "Descripción detallada del producto", example = "Montura ligera para uso diario")
                          String description,

                          @Schema(description = "Precio del producto", example = "149.99")
                          BigDecimal price,

                          @Schema(description = "Cantidad disponible en stock", example = "30")
                          long stock,

                          @Schema(description = "Marca del producto", example = "Ray-Ban")
                          String brand,

                          @Schema(description = "URL de la imagen del producto", example = "https://cdn.optimedica.com/img/lente123.jpg")
                          String imageURL,

                          @Schema(description = "Indicador de si el producto está activo (1) o inactivo (0)", example = "1")
                          long activeFlag,

                          @Schema(description = "Fecha de creación del producto", example = "2024-07-01T10:00:00")
                          Date createdAt
                         ) { }
