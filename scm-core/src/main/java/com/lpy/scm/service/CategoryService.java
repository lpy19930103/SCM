package com.lpy.scm.service;

import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.CategoryDO;
import com.lpy.scm.dto.CategoryDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddCategoryParam;

import java.util.List;

/**
 * @author lpy
 * @date 2019/2/15 16:24
 * @description 品类
 */
public interface CategoryService extends BaseService<CategoryDO> {

    List<CategoryDTO> getCategory() throws ParamException;

    CategoryDTO queryCategoryById(String id) throws ParamException;

    void addCategory(AddCategoryParam addCategoryParam) throws ParamException;

    void editCategory(AddCategoryParam addCategoryParam) throws ParamException;
}
