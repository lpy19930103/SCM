package com.lpy.scm.service.impl;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.service.SaleOrderService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
@Service
public class SaleOrderServiceImpl extends BaseServiceImpl<SaleOrderDO> implements SaleOrderService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void order(OrderParam orderParam) {

    }

    @Override
    public PageInfo<SaleOrderDO> orderList(int pageNo, int pageSize) {
        List<SaleOrderDO> saleOrderDOS = queryByPage(pageNo, pageSize);
        return new PageInfo<SaleOrderDO>(saleOrderDOS);
    }

    @Override
    public ProductDTO queryProductByCode(String code) throws ParamException {
        ProductDO productDO = new ProductDO();
        productDO.setCode(code);
        ProductDO productDO1 = productMapper.selectOne(productDO);
        AssertUtil.isNullObj(productDO1,"未查询到商品");
        return BeanUtil.convertObject(productDO1, ProductDTO.class);
    }


}
