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
    @Column(name = "created_at")
    private Date createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(long activeFlag) {
        this.activeFlag = activeFlag;
    }



    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
