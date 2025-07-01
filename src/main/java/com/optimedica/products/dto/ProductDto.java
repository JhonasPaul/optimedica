package com.optimedica.products.dto;

import com.optimedica.common.entity.BaseId;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductDto extends BaseId {

    private String name;
    private String description;
    private BigDecimal price;
    private long stock;
    private String brand;
    private String imageURL;
    private long activeFlag;
    private Date createdAt;
}
