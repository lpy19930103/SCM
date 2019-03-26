package com.lpy.scm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.DepotMapper;
import com.lpy.scm.dataobject.DepotDo;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddDepotParam;
import com.lpy.scm.service.DepotService;
import com.lpy.scm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lpy
 * @date 2019/3/26 15:13
 */
@Service
public class DepotServiceImpl extends BaseServiceImpl<DepotDo> implements DepotService {
    @Autowired
    private DepotMapper depotMapper;

    @Override
    public void addDepot(AddDepotParam addDepotParam) {
        DepotDo depotDo = new DepotDo();
        depotDo.setAdminId(addDepotParam.getAdminId());
        depotDo.setAdminName(addDepotParam.getAdminName());
        Date date = new Date();
        depotDo.setDepotDes(addDepotParam.getDepotDes());
        depotDo.setDepotAddress(addDepotParam.getDepotAddress());
        depotDo.setDepotName(addDepotParam.getDepotName());
        depotDo.setCreater("admin");
        depotDo.setCreateAt(date);
        depotMapper.insertSelective(depotDo);
    }

    @Override
    public PageInfo<DepotDo> getDepots(int pageNo, int pageSize) throws ParamException {
        List<DepotDo> depotDos = queryByPage(pageNo, pageSize);
        AssertUtil.isNullList(depotDos, "10001", "未查询到数据");
        return new PageInfo<DepotDo>(depotDos);
    }
}
