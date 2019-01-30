package com.lpy.scm.service;

import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddUserParam;
import com.lpy.scm.param.LoginParam;

public interface UserService {
    UserDO queryUserById(String id);

    UserDTO login(LoginParam loginParam) throws ParamException;

    void addUser(AddUserParam addUserParam) throws ParamException;

}
