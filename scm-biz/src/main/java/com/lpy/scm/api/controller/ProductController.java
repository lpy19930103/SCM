package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.ProductService;
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
 * @date 2019/2/19 15:09
 */
@Controller
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<List<ProductDTO>> list(ProductQueryParam queryParam) throws ParamException {
        PageInfo<ProductDO> list = productService.list(queryParam);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductDO p :
                list.getList()) {
            productDTOS.add(BeanUtil.convertObject(p, ProductDTO.class));
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
