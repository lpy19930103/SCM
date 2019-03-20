package com.lpy.scm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dao.StockMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dataobject.StockDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.dto.StockDTO;
import com.lpy.scm.exception.ErrorEnum;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.StockService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/4 18:56
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StockMapper mStockMapper;


    @Override
    public void editStockByCode(String productCode, int num, long price) throws ParamException {
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


    @Override
    public void deleteStockById(Long id) throws ParamException {
        StockDO stockDO = mStockMapper.selectByPrimaryKey(id);
        AssertUtil.isNullObj(stockDO, "未查询到该id对应的库存信息");
        ProductDO productDO = new ProductDO();
        productDO.setCode(stockDO.getGoodsCode());
        ProductDO productDO1 = productMapper.selectOne(productDO);
        AssertUtil.isNullObj(productDO1, "未查询到该id对应的商品信息");
        productDO1.setNum(0);
        Example example = new Example(ProductDO.class);
        example.createCriteria().andEqualTo("id", productDO.getId());
        productMapper.updateByExampleSelective(productDO1, example);
        mStockMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageInfo<StockDO> list(ProductQueryParam productQuertParam) throws ParamException {
        PageHelper.startPage(productQuertParam.getPageNo(), productQuertParam.getPageSize());
        List<StockDO> select = mStockMapper.queryStock(productQuertParam);
        AssertUtil.isNullList(select, "10001", "未查询到数据");
        return new PageInfo<>(select);
    }
}
