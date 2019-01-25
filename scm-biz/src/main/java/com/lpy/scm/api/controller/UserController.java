package com.lpy.scm.api.controller;

import com.lpy.scm.DO.UserDO;
import com.lpy.scm.exception.BusinessException;
import com.lpy.scm.service.UserService;
import com.lpy.scm.log.GlobalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private GlobalLog globalLog = new GlobalLog();

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserDO queryUserById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new RuntimeException("1111111");
        }
        UserDO userDO = userService.queryUserById(id);
        if (userDO == null) {
            throw new BusinessException("22222222", "hahahahahaha");
        }
        globalLog.warn("hahahahahahahahahahahah");
        return userDO;
    }

}
