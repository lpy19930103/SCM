package com.lpy.scm.dataobject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "order_item")
public class OrderItemDO implements Serializable {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "goods_id")
    private Long goodsId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_unit")
    private String goodsUnit;
    @Column(name = "goods_price")
    private Long price;
    @Column(name = "goods_image")
    private String goodsImage;
    @Column(name = "goods_num")
    private Integer num;
    @Column(name = "goods_total")
    private Long totalFee;
    @Column(name = "order_id")
    private Long orderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}