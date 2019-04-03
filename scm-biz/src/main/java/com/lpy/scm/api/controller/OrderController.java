package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
