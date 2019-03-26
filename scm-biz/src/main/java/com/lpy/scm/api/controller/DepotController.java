package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.DepotDo;
import com.lpy.scm.dto.DepotDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddDepotParam;
import com.lpy.scm.service.DepotService;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/3/26 15:09
 */
@Controller
@RequestMapping("depot")
public class DepotController {


    @Autowired
    private DepotService depotService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addDepot(AddDepotParam addDepotParam) {
        depotService.addDepot(addDepotParam);
        return ApiResponse.success().setMsg("添加成功");
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<DepotDTO> listDepot(int pageNo, int pageSize) throws ParamException {
        PageInfo<DepotDo> depots = depotService.getDepots(pageNo, pageSize);
        PageResponse<DepotDTO> depotDoPageResponse = new PageResponse<>();
        depotDoPageResponse.setTotal(depots.getTotal());
        depotDoPageResponse.setMsg("查询成功");
        depotDoPageResponse.setCode(0);
        List<DepotDTO> depotDTOS = new ArrayList<>();
        for (DepotDo depotDo : depots.getList()) {
            depotDTOS.add(BeanUtil.convertObject(depotDo, DepotDTO.class));
        }
        depotDoPageResponse.setData(depotDTOS);
        return depotDoPageResponse;
    }


}
