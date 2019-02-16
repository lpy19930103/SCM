package com.lpy.scm.service.impl;

import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.CategoryMapper;
import com.lpy.scm.dataobject.CategoryDO;
import com.lpy.scm.dataobject.EmpDO;
import com.lpy.scm.dto.CategoryDTO;
import com.lpy.scm.enums.system.GlobalIdBizType;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.manager.SystemConfigManager;
import com.lpy.scm.param.AddCategoryParam;
import com.lpy.scm.service.CategoryService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lpy
 * @date 2019/2/15 16:24
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDO> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    SystemConfigManager systemConfigManager;

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

    @Override
    public CategoryDTO queryCategoryById(String id) throws ParamException {
        CategoryDO categoryDO = categoryMapper.selectByPrimaryKey(id);
        AssertUtil.isNullObj(categoryDO, "未查询到该品类");
        return BeanUtil.convertObject(categoryDO, CategoryDTO.class);
    }

    @Override
    public void addCategory(AddCategoryParam addCategoryParam) throws ParamException {
        AssertUtil.isNullStr(addCategoryParam.getCategoryName(), ExceptionCode.BIZ_ERROR, "品类名称不能为空");
        AssertUtil.isNullStr(addCategoryParam.getCategoryUnit(), ExceptionCode.BIZ_ERROR, "计量单位不能为空");
        AssertUtil.isNullStr(addCategoryParam.getParentName(), ExceptionCode.BIZ_ERROR, "父品类不能为空");
        AssertUtil.isNullStr(addCategoryParam.getParentId(), ExceptionCode.BIZ_ERROR, "父品类不能为空");
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setCategoryName(addCategoryParam.getCategoryName());
        categoryDO.setCategoryUnit(addCategoryParam.getCategoryUnit());
        categoryDO.setParentId(addCategoryParam.getParentId());
        categoryDO.setParentName(addCategoryParam.getParentName());
        categoryDO.setMaterial(addCategoryParam.getMaterial());
        categoryDO.setStatus(addCategoryParam.getStatus());
        categoryDO.setCreateAt(new Date());
        String nextGlobalId = systemConfigManager.getNextGlobalId(GlobalIdBizType.SCM_CATEGORY);
        categoryDO.setId(nextGlobalId);
        categoryMapper.insert(categoryDO);
    }

    @Override
    public void editCategory(AddCategoryParam addCategoryParam) throws ParamException {
        AssertUtil.isNullStr(addCategoryParam.getCategoryName(), ExceptionCode.BIZ_ERROR, "品类名称不能为空");
        AssertUtil.isNullStr(addCategoryParam.getCategoryUnit(), ExceptionCode.BIZ_ERROR, "计量单位不能为空");
        AssertUtil.isNullStr(addCategoryParam.getParentName(), ExceptionCode.BIZ_ERROR, "父品类不能为空");
        AssertUtil.isNullStr(addCategoryParam.getParentId(), ExceptionCode.BIZ_ERROR, "父品类不能为空");
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setParentName(addCategoryParam.getParentName());
        categoryDO.setParentId(addCategoryParam.getParentId());
        categoryDO.setCategoryName(addCategoryParam.getCategoryName());
        categoryDO.setCategoryUnit(addCategoryParam.getCategoryUnit());
        if (!StringUtils.isEmpty(addCategoryParam.getMaterial())) {
            categoryDO.setMaterial(addCategoryParam.getMaterial());
        }
        categoryDO.setStatus(addCategoryParam.getStatus());
        categoryDO.setUpdateAt(new Date());
        Example example = new Example(CategoryDO.class);
        example.createCriteria().andEqualTo("id", addCategoryParam.getId());
        categoryMapper.updateByExampleSelective(categoryDO, example);
    }
}
