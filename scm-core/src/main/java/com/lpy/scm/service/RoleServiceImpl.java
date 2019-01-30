package com.lpy.scm.service;

import com.lpy.scm.dao.RoleMapper;
import com.lpy.scm.dataobject.RoleDO;
import com.lpy.scm.dto.RoleDTO;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpy
 * @date 2019/1/30 11:03
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getRoles() {
        List<RoleDO> roleDOS = roleMapper.select(null);
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (RoleDO roleDo : roleDOS) {
            roleDTOS.add(BeanUtil.convertObject(roleDo, RoleDTO.class));
        }
        return roleDTOS;
    }
}
