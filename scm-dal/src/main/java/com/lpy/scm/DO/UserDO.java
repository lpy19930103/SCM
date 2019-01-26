package com.lpy.scm.DO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "sys_user")
public class UserDO extends BaseDO{

    @Id
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_pwd")
    private String userPwd;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "emp_id")
    private Long empId;
    @Column
    private Integer status;
    @Column(name = "is_locked")
    private Integer isLocked;
    @Column(name = "user_pwd")
    private Date lockedTo;

}
