package com.lpy.scm.service;

import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.CategoryDO;
import com.lpy.scm.dto.CategoryDTO;
import com.lpy.scm.exception.ParamException;

import java.util.List;

/**
 * @author lpy
 * @date 2019/2/15 16:24
 */
public interface CategoryService extends BaseService<CategoryDO> {
    List<CategoryDTO> getCategory() throws ParamException;
}
