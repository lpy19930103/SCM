package com.lpy.scm.param;

import com.lpy.scm.dto.OrderItemDTO;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:50
 */
public class OrderParam {

    private Long goodsTotal;

    private Long customerId;

    private String customerName;

    private String customerPhone;

    private List<OrderItemDTO> items;

    public Long getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(Long goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
