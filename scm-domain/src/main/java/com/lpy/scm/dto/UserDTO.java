package com.lpy.scm.dto;

import java.util.Date;

/**
 * @author lpy
 * @date 2019/1/26 9:53
 */
public class UserDTO {

    private String userId;
    private String userName;
    private Long roleId;
    private String roleName;
    private Long empId;
    private Integer status;
    private Integer isLocked;
    private Date lockedTo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLockedTo() {
        return lockedTo;
    }

    public void setLockedTo(Date lockedTo) {
        this.lockedTo = lockedTo;
    }
}