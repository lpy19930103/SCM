package com.lpy.scm.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/3/15 17:46
 */

@Table(name = "stock")
public class StockDTO {
    public Long id;
    public String name;
    public String code;
    public String categoryName;
    public String categoryId;
    public String image;
    public Integer num;
    public Long purchasePrice;
    public Long salePrice;
    public String unit;
    private Date storageAt;
    private String storager;

    public Date getStorageAt() {
        return storageAt;
    }

    public void setStorageAt(Date storageAt) {
        this.storageAt = storageAt;
    }

    public String getStorager() {
        return storager;
    }

    public void setStorager(String storager) {
        this.storager = storager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
