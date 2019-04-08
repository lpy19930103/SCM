package com.lpy.scm.dto;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/3/28 16:16
 */
public class SaleOrderDTO {
    @Id
    private String id;

    private Long goodsTotal;

    private Long customerId;

    private String customerName;

    private String customerPhone;

    private Long empId;

    private String saleDes;

    private Integer reviewStatus;

    private String reviewName;

    private Date reviewAt;

    private Date outTime;

    //1:下单未付款,2:已付款,3已出库,4已配送,5申请退款,待审核,6,同意退款,退款中,7已退款,8已完成,9已关闭
    private Integer orderType;

    private Long depotId;

    private String depotName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getSaleDes() {
        return saleDes;
    }

    public void setSaleDes(String saleDes) {
        this.saleDes = saleDes;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public Date getReviewAt() {
        return reviewAt;
    }

    public void setReviewAt(Date reviewAt) {
        this.reviewAt = reviewAt;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

}
