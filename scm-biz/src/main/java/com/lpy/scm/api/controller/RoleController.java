package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.RoleDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lpy
 * @date 2019/1/30 10:28
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<RoleDTO> getRoles() throws ParamException {
        return ApiResponse.<RoleDTO>success().setData(roleService.getRoles());
    }
}
