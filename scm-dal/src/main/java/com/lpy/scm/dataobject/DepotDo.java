package com.lpy.scm.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/3/26 14:51
 */
@Table(name = "base_depot")
public class DepotDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depot_name")
    private String depotName;

    @Column(name = "depot_address")
    private String depotAddress;

    @Column(name = "depot_des")
    private String depotDes;
    @Column(name = "admin_id")
    private Long adminId;
    @Column(name = "admin_name")
    private String adminName;

    @Column
    private String creater;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
    }

    public String getDepotDes() {
        return depotDes;
    }

    public void setDepotDes(String depotDes) {
        this.depotDes = depotDes;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
