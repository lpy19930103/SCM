package com.lpy.scm.service;

import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lpy
 * @date 2019/3/4 18:56
 * @description 库存管理
 */
public interface StockService {

    ProductDTO queryProductByCode( String productCode);
    void editProductByCode( String productCode,int num,long price) throws ParamException;
}
