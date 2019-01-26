package com.lpy.scm.dao;

import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.param.LoginParam;
import tk.mybatis.mapper.common.Mapper;


public interface UserMapper extends Mapper<UserDO> {
    UserDO queryUserById(String id);

    UserDO login(LoginParam loginParam);

    UserDO loginCheck(LoginParam loginParam);

}
