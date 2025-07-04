package com.optimedica.products.mapper;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    ProductMapper MAPPER = Mappers.getMapper( ProductMapper.class );

    ProductDto toDto(Product product);

    Product toProduct(ProductDto productDto);

    List<ProductDto> toListDto(List<Product> products);
}
