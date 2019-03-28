package com.lpy.scm.service.impl;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.service.SaleOrderService;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
public class SaleOrderServiceImpl extends BaseServiceImpl<SaleOrderDO> implements SaleOrderService {
    @Override
    public void order(OrderParam orderParam) {

    }

    @Override
    public PageInfo<SaleOrderDO> orderList(int pageNo, int pageSize) {
        List<SaleOrderDO> saleOrderDOS = queryByPage(pageNo, pageSize);
        return new PageInfo<SaleOrderDO>(saleOrderDOS);
    }


}
