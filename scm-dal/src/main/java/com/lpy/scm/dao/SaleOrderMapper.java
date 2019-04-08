package com.lpy.scm.dao;

import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.param.QueryOrderParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author lpy
 * @date 2019/3/28 16:43
 */
public interface SaleOrderMapper extends Mapper<SaleOrderDO> {
    //关闭订单
    void closeOrderTask();

    List<SaleOrderDO> queryOrder(QueryOrderParam queryOrderParam);
}
