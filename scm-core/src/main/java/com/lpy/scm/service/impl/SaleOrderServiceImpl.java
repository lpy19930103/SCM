package com.lpy.scm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.OrderItemMapper;
import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dao.SaleOrderMapper;
import com.lpy.scm.dataobject.OrderItemDO;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dataobject.SaleOrderDO;
import com.lpy.scm.dto.OrderItemDTO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.enums.system.GlobalIdBizType;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.manager.SystemConfigManager;
import com.lpy.scm.param.OrderParam;
import com.lpy.scm.param.QueryOrderParam;
import com.lpy.scm.service.SaleOrderService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lpy
 * @date 2019/3/28 16:45
 */
@Service
public class SaleOrderServiceImpl extends BaseServiceImpl<SaleOrderDO> implements SaleOrderService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private SystemConfigManager systemConfigManager;


    //todo 商品对应仓库查询 ,商品数量增加,事务
    @Override
    public void order(OrderParam orderParam) {
        String nextGlobalId = systemConfigManager.getNextGlobalId(GlobalIdBizType.SCM_ORDER);
        ArrayList<OrderItemDO> orderItemDOS = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderParam.getItems()) {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(nextGlobalId);
            orderItemDO.setGoodsId(orderItemDTO.getGoodsId());
            orderItemDO.setGoodsName(orderItemDTO.getGoodsName());
            orderItemDO.setGoodsImage(orderItemDTO.getGoodsImage());
            orderItemDO.setNum(orderItemDTO.getNum());
            orderItemDO.setPrice(orderItemDTO.getPrice());
            orderItemDO.setTotalFee(orderItemDTO.getTotalFee());
            orderItemDO.setTotalFee(orderItemDTO.getTotalFee());
            orderItemDO.setGoodsUnit(orderItemDTO.getGoodsUnit());
            orderItemDOS.add(orderItemDO);
        }
        orderItemMapper.addOrderItem(orderItemDOS);

        SaleOrderDO saleOrderDO = new SaleOrderDO();
        saleOrderDO.setCreateAt(new Date());
        saleOrderDO.setReviewStatus(0);

        saleOrderDO.setCustomerName(orderParam.getCustomerName());
        saleOrderDO.setCustomerPhone(orderParam.getCustomerPhone());
        saleOrderDO.setGoodsTotal(orderParam.getGoodsTotal());
        saleOrderDO.setOrderType(1);
        saleOrderDO.setId(nextGlobalId);
        saleOrderMapper.insertSelective(saleOrderDO);

    }


    @Override
    public ProductDTO queryProductByCode(String code) throws ParamException {
        ProductDO productDO = new ProductDO();
        productDO.setCode(code);
        ProductDO productDO1 = productMapper.selectOne(productDO);
        AssertUtil.isNullObj(productDO1, "未查询到商品");
        return BeanUtil.convertObject(productDO1, ProductDTO.class);
    }

    @Override
    public PageInfo<SaleOrderDO> orderList(QueryOrderParam queryOrderParam) throws ParamException {
        PageHelper.startPage(queryOrderParam.getPageNo(), queryOrderParam.getPageSize());
        List<SaleOrderDO> saleOrderDOS = saleOrderMapper.queryOrder(queryOrderParam);
        AssertUtil.isNullList(saleOrderDOS, "10001", "未查询到数据");
        return new PageInfo<>(saleOrderDOS);
    }


}
