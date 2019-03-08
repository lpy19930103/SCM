package com.lpy.scm.service.impl;

import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ErrorEnum;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.service.StockService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public void editProductByCode(String productCode, int num, long price) throws ParamException {
        ProductDO productDO = new ProductDO();
        productDO.setCode(productCode);
        ProductDO productDO1 = productMapper.selectOne(productDO);
        AssertUtil.isNullObj(productDO1, "未查询到该商品");
        productDO1.setNum(num);
        productDO1.setSalePrice(price);
        Example example = new Example(ProductDO.class);
        example.createCriteria().andEqualTo("id", productDO1.getId());
        productMapper.updateByExampleSelective(productDO1, example);
    }
}
