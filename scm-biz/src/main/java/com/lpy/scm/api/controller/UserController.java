package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.param.LoginParam;
import com.lpy.scm.service.UserService;
import com.lpy.scm.log.GlobalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<UserDTO> login(LoginParam param, HttpServletResponse response) throws Exception {
        ApiResponse instance = ApiResponse.<UserDTO>instance();
        instance.setData(userService.login(param));
        instance.setMsg("登录成功");
        return instance;
    }


}
