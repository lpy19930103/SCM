package com.lpy.scm.api.controller;

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

    @RequestMapping("{code}")
    @ResponseBody
    public void queryProductByCode(@PathVariable(value = "code") String productCode, Integer num, Long price) {
    }
}
