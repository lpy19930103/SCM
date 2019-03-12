package com.lpy.scm.api.controller;

import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lpy
 * @date 2019/3/4 18:57
 */
@Controller
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("{code}")
    @ResponseBody
    public ApiResponse<ProductDTO> queryProductByCode(@PathVariable(value = "code") String productCode) {
        return ApiResponse.success().setData(stockService.queryProductByCode(productCode));
    }

    @RequestMapping("edit")
    @ResponseBody
    public ApiResponse<ProductDTO> editProductByCode(String barCode, Integer productNum, Long purchasePrice) throws ParamException {
        stockService.editProductByCode(barCode, productNum, purchasePrice);
        return ApiResponse.success().setData(null);
    }
}
