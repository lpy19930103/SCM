package com.lpy.scm.api.controller;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.bean.PageResponse;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddProductParam;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.ProductService;
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
 * @date 2019/2/19 15:09
 * @description 商品管理
 */
@Controller
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("{id}")
    @ResponseBody
    public ApiResponse<ProductDTO> queryProductById(@PathVariable(value = "id") Long id) {
        return ApiResponse.<ProductDTO>success().setMsg("查询商品成功").setData(productService.queryProductById(id));
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<List<ProductDTO>> list(ProductQueryParam queryParam) throws ParamException {
        PageInfo<ProductDO> list = productService.list(queryParam);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductDO p : list.getList()) {
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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addProduct(AddProductParam productParam) {
        productService.addProduct(productParam);
        return ApiResponse.success().setMsg("添加成功");
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse editProduct(AddProductParam productParam) {
        productService.editProduct(productParam);
        return ApiResponse.success().setMsg("添加成功");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return ApiResponse.success().setMsg("删除成功");
    }
}
