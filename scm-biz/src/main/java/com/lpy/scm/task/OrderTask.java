package com.lpy.scm.task;

import com.lpy.scm.dao.SaleOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author lpy
 * @date 2019/4/4 14:37
 */
@Component
public class OrderTask {
    @Autowired
    private SaleOrderMapper saleOrderMapper;


    @Scheduled(cron = "* 0/10 * * * ? ") // 间隔10分执行
    public void orderClose() {
        saleOrderMapper.closeOrderTask();
        System.out.println("批量关闭未支付订单");
    }


}
