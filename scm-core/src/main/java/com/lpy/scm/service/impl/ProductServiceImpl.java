package com.lpy.scm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.ProductMapper;
import com.lpy.scm.dataobject.ProductDO;
import com.lpy.scm.dto.ProductDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.ProductQueryParam;
import com.lpy.scm.service.ProductService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/2/19 15:19
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductDO> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> list(ProductQueryParam productQueryParam) throws ParamException {
        List<ProductDO> select = productMapper.queryProduct(productQueryParam);
        AssertUtil.isNullList(select, "10001", "未查询到数据");
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductDO p :
                select) {
            productDTOS.add(BeanUtil.convertObject(p, ProductDTO.class));
        }
        return productDTOS;
    }
}
