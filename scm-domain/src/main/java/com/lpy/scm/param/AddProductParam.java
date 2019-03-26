package com.lpy.scm.param;

/**
 * @author lpy
 * @date 2019/2/22 14:20
 */
public class AddProductParam {
    private Long id;
    private String code;
    private String productName;
//    private Float productPrice;
//    private Float purchasePrice;
//    private Integer storageNum;
    private String brand;
    private String productUnit;
    private String productIntro;
    private String productPartName;
    private String productPartId;

    public String getProductPartName() {
        return productPartName;
    }

    public void setProductPartName(String productPartName) {
        this.productPartName = productPartName;
    }

    public String getProductPartId() {
        return productPartId;
    }

    public void setProductPartId(String productPartId) {
        this.productPartId = productPartId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//    public Float getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(Float productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public Float getPurchasePrice() {
//        return purchasePrice;
//    }
//
//    public void setPurchasePrice(Float purchasePrice) {
//        this.purchasePrice = purchasePrice;
//    }
//
//    public Integer getStorageNum() {
//        return storageNum;
//    }
//
//    public void setStorageNum(Integer storageNum) {
//        this.storageNum = storageNum;
//    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }
}
