package com.lpy.scm.dao;

import com.lpy.scm.dataobject.SaleOrderDO;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author lpy
 * @date 2019/3/28 16:43
 */
public interface SaleOrderMapper extends Mapper<SaleOrderDO> {
    //关闭订单
    void closeOrderTask();
}
