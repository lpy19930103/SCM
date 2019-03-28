package com.lpy.scm.service.impl;

import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.OrderItemMapper;
import com.lpy.scm.dataobject.OrderItemDO;
import com.lpy.scm.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemDO> implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDO> queryItemByOrderId(Long orderId) {
        OrderItemDO orderItemDO = new OrderItemDO();
        orderItemDO.setOrderId(orderId);
        return orderItemMapper.select(orderItemDO);
    }
}
