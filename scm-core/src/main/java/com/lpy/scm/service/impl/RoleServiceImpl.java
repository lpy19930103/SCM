package com.lpy.scm.service.impl;

import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dataobject.RoleDO;
import com.lpy.scm.dto.RoleDTO;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.service.RoleService;
import com.lpy.scm.utils.AssertUtil;
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
    public List<RoleDTO> getRoles() throws ParamException {
        List<RoleDO> roleDOS = queryAll();
        AssertUtil.isNullList(roleDOS, ExceptionCode.BIZ_ERROR, "未查询到数据");
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (RoleDO roleDo : roleDOS) {
            roleDTOS.add(BeanUtil.convertObject(roleDo, RoleDTO.class));
        }
        return roleDTOS;
    }
}
