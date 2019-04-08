package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.param.QueryOrderParam;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
public interface SaleOrderService extends BaseService<SaleOrderDO> {


    void order(OrderParam orderParam);

    ProductDTO queryProductByCode(String code) throws ParamException;

    PageInfo<SaleOrderDO> orderList(QueryOrderParam queryOrderParam) throws ParamException;

}
