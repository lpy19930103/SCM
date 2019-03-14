package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<List<ProductDTO>> list(ProductQueryParam queryParam) throws ParamException {
        PageInfo<ProductDO> list = stockService.list(queryParam);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductDO p : list.getList()) {
            if (p.getNum() > 0) {
                productDTOS.add(BeanUtil.convertObject(p, ProductDTO.class));
            }
        }
        PageResponse<ProductDTO> productDTOPageResponse = new PageResponse<>();
        productDTOPageResponse.setPage(list.getPageNum());
        productDTOPageResponse.setRows(list.getPageSize());
        productDTOPageResponse.setData(productDTOS);
        productDTOPageResponse.setTotal(list.getTotal());
        productDTOPageResponse.setCode(0);
        productDTOPageResponse.setMsg("查询成功");
        return productDTOPageResponse;
    }
}
