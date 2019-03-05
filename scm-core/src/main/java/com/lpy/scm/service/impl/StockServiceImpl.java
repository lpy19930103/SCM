package com.lpy.scm.service.impl;

import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.service.StockService;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lpy
 * @date 2019/3/4 18:56
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO queryProductByCode(String productCode) {
        ProductDO productDO = new ProductDO();
        productDO.setCode(productCode);
        return BeanUtil.convertObject(productMapper.selectOne(productDO), ProductDTO.class);
    }
}
