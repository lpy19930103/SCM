package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.param.OrderParam;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
public interface SaleOrderService extends BaseService<SaleOrderDO> {


    void order(OrderParam orderParam);

    PageInfo<SaleOrderDO> orderList(int pageNo, int pageSize);

}
