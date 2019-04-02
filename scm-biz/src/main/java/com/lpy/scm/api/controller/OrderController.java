package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ApiResponse queryProductByCode(OrderParam orderParam) throws ParamException {
        saleOrderService.order(orderParam);
        return ApiResponse.success().setMsg("下单成功");
    }

}
