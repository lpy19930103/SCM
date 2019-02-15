package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.exception.BizException;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddUserParam;
import com.lpy.scm.param.LoginParam;
import com.lpy.scm.service.UserService;
import com.lpy.scm.log.GlobalLog;
import com.lpy.scm.service.impl.EditUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private GlobalLog globalLog = new GlobalLog();

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<UserDTO> queryUserById(@PathVariable("id") String id) throws Exception {
        if (id == null) {
            throw new ParamException(ExceptionCode.PARAM_ERROR, "用户id不能为空");
        }
        return ApiResponse.instance().setData(userService.queryUserById(id));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<UserDTO> login(LoginParam param, HttpServletResponse response) throws Exception {
        return ApiResponse.<UserDTO>instance().setData(userService.login(param)).setMsg("登录成功");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addUser(AddUserParam addUserParam) throws ParamException {
        userService.addUser(addUserParam);
        return ApiResponse.success().setMsg("添加用户成功");
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getUsers() {
        return ApiResponse.success().setData(userService.getUsers());
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse updateUser(EditUserParam editUserParam) {
        userService.updateUsers(editUserParam);
        return ApiResponse.instance().setMsg("修改成功");
    }

    @RequestMapping(value = "editPwd", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse editPwd(String userName, String oldPass, String newPass) throws ParamException {
        userService.editPwd(userName, oldPass, newPass);
        return ApiResponse.instance().setMsg("修改成功");
    }

}
