package com.lpy.scm.service.impl;

import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.CategoryMapper;
import com.lpy.scm.dataobject.CategoryDO;
import com.lpy.scm.dto.CategoryDTO;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.service.CategoryService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/2/15 16:24
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDO> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getCategory() throws ParamException {
        List<CategoryDO> categoryDOS = queryAll();
        AssertUtil.isNullList(categoryDOS, ExceptionCode.BIZ_ERROR, "未查询到数据");
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryDO c : categoryDOS) {
            categoryDTOS.add(BeanUtil.convertObject(c, CategoryDTO.class));
        }
        return categoryDTOS;
    }
}
