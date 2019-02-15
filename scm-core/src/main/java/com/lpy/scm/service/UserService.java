package com.lpy.scm.service;

import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddUserParam;
import com.lpy.scm.param.LoginParam;
import com.lpy.scm.service.impl.EditUserParam;

import java.util.List;

public interface UserService {
    UserDTO queryUserById(String id);

    UserDTO login(LoginParam loginParam) throws ParamException;

    void addUser(AddUserParam addUserParam) throws ParamException;

    List<UserDTO> getUsers();

    void updateUsers(EditUserParam editUserParam);

    void editPwd(String userName,String oldPass, String newPass) throws ParamException;

}
