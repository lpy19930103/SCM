package com.lpy.scm.service;

import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;

import java.util.List;

/**
 * @author lpy
 * @date 2019/2/19 15:10
 */
public interface ProductService extends BaseService<ProductDO> {

    List<ProductDTO> list(ProductQueryParam productQuertParam) throws ParamException;
}
