package com.lpy.scm.dto;

/**
 * @author lpy
 * @date 2019/3/26 14:51
 */
public class DepotDTO {
    private Long id;
    private String depotName;
    private String depotAddress;
    private String depotDes;
    private Long adminId;
    private String adminName;

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

}
