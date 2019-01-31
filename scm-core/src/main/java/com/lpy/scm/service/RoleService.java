package com.lpy.scm.service;

import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.RoleDO;
import com.lpy.scm.dto.RoleDTO;

import java.util.List;

/**
 * @author lpy
 * @date 2019/1/30 10:59
 */
public interface RoleService extends BaseService<RoleDO> {
    List<RoleDTO> getRoles();
}
