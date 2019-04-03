package com.lpy.scm.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/3/28 16:16
 */
@Table(name = "sale_order")
public class SaleOrderDO {
    @Id
    private String id;

    @Column(name = "goods_total")
    private Long goodsTotal;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "sale_des")
    private String saleDes;

    @Column(name = "review_status")
    private Integer reviewStatus;

    @Column(name = "review_name")
    private String reviewName;

    @Column(name = "review_at")
    private Date reviewAt;

    @Column(name = "out_time")
    private Date outTime;

    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "depot_id")
    private Long depotId;

    @Column(name = "depot_name")
    private String depotName;

    @Column
    private String creater;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
