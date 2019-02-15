package com.lpy.scm.dao;

import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.param.LoginParam;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lpy
 * @date 2019/2/15 16:19
 * @description 用户
 */
public interface UserMapper extends Mapper<UserDO> {
    UserDO queryUserById(String id);

    UserDO login(LoginParam loginParam);

    UserDO loginCheck(LoginParam loginParam);

}
