package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dataobject.StockDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.dto.StockDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.StockService;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/3/4 18:57
 */
@Controller
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("{id}")
    @ResponseBody
    public ApiResponse<StockDTO> queryStockById(@PathVariable(value = "id") Long id) {
        return ApiResponse.success().setData(stockService.queryStockById(id));
    }

    @RequestMapping("add")
    @ResponseBody
    public ApiResponse addStockByCode(String barCode, Integer productNum, Long purchasePrice) throws ParamException {
        stockService.addStockByCode(barCode, productNum, purchasePrice);
        return ApiResponse.success();
    }

    @RequestMapping("edit")
    @ResponseBody
    public ApiResponse editStockByCode(String barCode, Integer productNum, Long purchasePrice, Long salePrice) throws ParamException {
        stockService.editStockByCode(barCode, productNum, purchasePrice, salePrice);
        return ApiResponse.success();
    }


    @RequestMapping("delete/{id}")
    @ResponseBody
    public ApiResponse deleteStockByCode(@PathVariable(value = "id") Long id) throws ParamException {
        stockService.deleteStockById(id);
        return ApiResponse.success();
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<StockDTO> list(ProductQueryParam queryParam) throws ParamException {
        PageInfo<StockDO> list = stockService.list(queryParam);
        ArrayList<StockDTO> productDTOS = new ArrayList<>();

        for (StockDO p : list.getList()) {
            productDTOS.add(BeanUtil.convertObject(p, StockDTO.class));
        }
        PageResponse<StockDTO> productDTOPageResponse = new PageResponse<>();
        productDTOPageResponse.setPage(list.getPageNum());
        productDTOPageResponse.setRows(list.getPageSize());
        productDTOPageResponse.setData(productDTOS);
        productDTOPageResponse.setTotal(list.getTotal());
        productDTOPageResponse.setCode(0);
        productDTOPageResponse.setMsg("查询成功");
        return productDTOPageResponse;
    }
}
