package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.dto.SaleOrderDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.param.QueryOrderParam;
import com.lpy.scm.service.SaleOrderService;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author lpy
 * @date 2019/3/28 16:43
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    @RequestMapping("product/{code}")
    @ResponseBody
    public ApiResponse<ProductDTO> queryProductByCode(@PathVariable(value = "code") String code) throws ParamException {
        return ApiResponse.success().setData(saleOrderService.queryProductByCode(code));
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse queryProductByCode(@RequestBody OrderParam orderParam) throws ParamException {
        saleOrderService.order(orderParam);
        return ApiResponse.success().setMsg("下单成功");
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<SaleOrderDTO> list(QueryOrderParam queryOrderParam) throws ParamException {
        PageInfo<SaleOrderDO> list = saleOrderService.orderList(queryOrderParam);
        PageResponse<SaleOrderDTO> saleOrderDTOPageResponse = new PageResponse<>();
        ArrayList<SaleOrderDTO> saleOrderDTOS = new ArrayList<>();
        for (SaleOrderDO saleOrderDO : list.getList()) {
            saleOrderDTOS.add(BeanUtil.convertObject(saleOrderDO, SaleOrderDTO.class));
        }
        saleOrderDTOPageResponse.setData(saleOrderDTOS);
        saleOrderDTOPageResponse.setCode(0);
        saleOrderDTOPageResponse.setTotal(list.getTotal());
        saleOrderDTOPageResponse.setMsg("查询成功");
        return saleOrderDTOPageResponse;
    }
}
