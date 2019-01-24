package com.lpy.scm.DO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "user")
public class UserDO {

    @Id
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column
    private String phone;
}
