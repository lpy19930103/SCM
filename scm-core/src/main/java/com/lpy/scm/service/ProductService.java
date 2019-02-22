package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddProductParam;
import com.lpy.scm.param.ProductQueryParam;


/**
 * @author lpy
 * @date 2019/2/19 15:10
 */
public interface ProductService extends BaseService<ProductDO> {

    PageInfo<ProductDO> list(ProductQueryParam productQuertParam) throws ParamException;

    void addProduct(AddProductParam addProductParam);
}
