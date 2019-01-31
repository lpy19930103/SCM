package com.lpy.scm.service.impl;

import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dataobject.RoleDO;
import com.lpy.scm.dto.RoleDTO;
import com.lpy.scm.service.RoleService;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/1/30 11:03
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDO> implements RoleService {

    @Override
    public List<RoleDTO> getRoles() {
        List<RoleDO> roleDOS = queryAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (RoleDO roleDo : roleDOS) {
            roleDTOS.add(BeanUtil.convertObject(roleDo, RoleDTO.class));
        }
        return roleDTOS;
    }
}
