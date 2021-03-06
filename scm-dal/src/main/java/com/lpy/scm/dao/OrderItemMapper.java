package com.lpy.scm.dao;

import com.lpy.scm.dataobject.OrderItemDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author lpy
 * @date 2019/3/28 16:44
 */
public interface OrderItemMapper extends Mapper<OrderItemDO> {
    void addOrderItem(List<OrderItemDO> orderItemDOS);
}
