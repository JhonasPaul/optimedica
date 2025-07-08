package com.optimedica.products.mapper;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    ProductMapper MAPPER = Mappers.getMapper( ProductMapper.class );

    ProductDto toDto(Product product);

    Product toProduct(ProductDto productDto);

    List<ProductDto> toListDto(List<Product> products);

    @Mapping(target = "id", ignore = true) // si no quieres sobrescribir el ID
    @Mapping(target = "createdAt", ignore = true) // si quieres mantener la fecha original
    void updateProductFromDto(ProductDto dto, @MappingTarget Product entity);

}
