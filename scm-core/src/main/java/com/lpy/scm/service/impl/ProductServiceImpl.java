package com.lpy.scm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddProductParam;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.ProductService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lpy
 * @date 2019/2/19 15:19
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductDO> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<ProductDO> list(ProductQueryParam productQueryParam) throws ParamException {
        PageHelper.startPage(productQueryParam.getPageNo(), productQueryParam.getPageSize());
        List<ProductDO> select = productMapper.queryProduct(productQueryParam);
        AssertUtil.isNullList(select, "10001", "未查询到数据");
        PageInfo<ProductDO> productDOPageInfo = new PageInfo<>(select);
        return productDOPageInfo;
    }

    @Override
    public void addProduct(AddProductParam addProductParam) {
        ProductDO productDO = new ProductDO();
        productDO.setBrand(addProductParam.getBrand());
        productDO.setCategoryId(addProductParam.getProductPart());
        productDO.setName(addProductParam.getProductName());
        productDO.setCode(addProductParam.getCode());
        productDO.setPurchasePrice(addProductParam.getPurchasePrice());
        productDO.setSalePrice(addProductParam.getProductPrice());
        productDO.setName(addProductParam.getProductName());
        productDO.setUnit(addProductParam.getProductUnit());
        productDO.setDes(addProductParam.getProductIntro());
        productDO.setCreateAt(new Date());
        productMapper.insertSelective(productDO);
    }
}
