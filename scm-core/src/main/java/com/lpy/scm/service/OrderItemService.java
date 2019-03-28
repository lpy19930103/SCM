package com.lpy.scm.service;

import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.OrderItemDO;
import com.lpy.scm.dataobject.SaleOrderDO;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
public interface OrderItemService extends BaseService<OrderItemDO> {
    List<OrderItemDO> queryItemByOrderId(Long orderId);
}
