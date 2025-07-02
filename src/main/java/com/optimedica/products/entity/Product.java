package com.optimedica.products.entity;

import com.optimedica.common.entity.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Table
@Entity(name = "products")
public class Product extends BaseId {


    private String name;
    private String description;
    private BigDecimal price;
    private long stock;
    private String brand;
    @Column(name = "image_url")
    private String imageURL;
    @Column(name = "active_flag")
    private long activeFlag;
    @Column(name = "category_id")
    private Integer categoryID;
    @Column(name = "created_at")
    private Date createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
