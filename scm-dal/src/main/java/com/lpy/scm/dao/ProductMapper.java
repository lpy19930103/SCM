package com.lpy.scm.dao;


import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.param.ProductQueryParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lpy
 * @date 2019/2/19 15:19
 * @description 商品
 */

public interface ProductMapper extends Mapper<ProductDO> {
    List<ProductDO> queryProduct(ProductQueryParam productQuertParam);
}
