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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
