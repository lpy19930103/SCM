package com.lpy.scm.dataobject;

import javax.persistence.*;

/**
 * @author lpy
 * @date 2019/3/15 17:46
 */

@Table(name = "stock_manager")
public class StockDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "goods_code")
    private Long goodsCode;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_image")
    private String goodsImage;

    @Column(name = "sale_num")
    private int saleNum;
    @Column(name = "stock_num")
    private int stockNum;
    @Column(name = "stock_total")
    private int stockTotal;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "depot_id")
    private Long depotId;
    @Column(name = "depot_name")
    private String depotName;

    @Column(name = "sale_price")
    private Long salePrice;
    @Column(name = "purchase_price")
    private Long purchasePrice;

    @Column
    private String creater;
    @Column(name = "update_at")
    private String updateAt;
    @Column(name = "create_at")
    private String createAt;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
