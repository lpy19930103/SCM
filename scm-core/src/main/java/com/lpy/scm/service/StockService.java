package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;

import com.lpy.scm.dataobject.StockDO;
import com.lpy.scm.dto.StockDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;

/**
 * @author lpy
 * @date 2019/3/4 18:56
 * @description 库存管理
 */
public interface StockService {

    void deleteStockById(Long id) throws ParamException;

    void editStockByCode(String StockCode, int num, long price, Long salePrice) throws ParamException;

    void addStockByCode(String StockCode, int num, long price) throws ParamException;

    StockDTO queryStockById(Long id);

    PageInfo<StockDO> list(ProductQueryParam StockQuertParam) throws ParamException;
}
