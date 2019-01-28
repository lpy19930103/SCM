package com.lpy.scm.api.controller;

import com.alibaba.fastjson.JSON;
import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.exception.BizException;
import com.lpy.scm.param.LoginParam;
import com.lpy.scm.service.UserService;
import com.lpy.scm.log.GlobalLog;
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
    public UserDO queryUserById(@PathVariable("id") String id) {
        if (id == null) {
            throw new BizException("10000", "测试异常响应");
        }
        UserDO userDO = userService.queryUserById(id);
        if (userDO == null) {
            throw new BizException("10001", "测试异常响应");
        }
        globalLog.warn("测试log");
        return userDO;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<UserDTO> login(LoginParam param, HttpServletResponse response) throws Exception {
        ApiResponse instance = ApiResponse.<UserDTO>instance();
        instance.setData(userService.login(param));
        instance.setMsg("登录成功");
        return instance;
    }

}
