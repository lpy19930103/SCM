package com.lpy.scm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.impl.BaseServiceImpl;
import com.lpy.scm.dao.DepotMapper;
import com.lpy.scm.dataobject.DepotDo;
import com.lpy.scm.dto.DepotDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddDepotParam;
import com.lpy.scm.param.EditDepotParam;
import com.lpy.scm.service.DepotService;
import com.lpy.scm.utils.AssertUtil;
import com.lpy.scm.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        depotDo.setDepotDes(addDepotParam.getDepotDes());
        depotDo.setDepotAddress(addDepotParam.getDepotAddress());
        depotDo.setDepotName(addDepotParam.getDepotName());
        depotDo.setCreater("admin");
        depotDo.setCreateAt(new Date());
        depotMapper.insertSelective(depotDo);
    }

    @Override
    public void editDepot(EditDepotParam editDepotParam) {
        DepotDo depotDo = new DepotDo();
        depotDo.setAdminId(editDepotParam.getAdminId());
        depotDo.setAdminName(editDepotParam.getAdminName());
        depotDo.setDepotDes(editDepotParam.getDepotDes());
        depotDo.setDepotAddress(editDepotParam.getDepotAddress());
        depotDo.setDepotName(editDepotParam.getDepotName());
        depotDo.setUpdateAt(new Date());
        Example example = new Example(DepotDo.class);
        example.createCriteria().andEqualTo("id", editDepotParam.getId());
        depotMapper.updateByExampleSelective(depotDo, example);
    }

    @Override
    public PageInfo<DepotDo> getDepots(int pageNo, int pageSize) throws ParamException {
        List<DepotDo> depotDos = queryByPage(pageNo, pageSize);
        AssertUtil.isNullList(depotDos, "10001", "未查询到数据");
        return new PageInfo<DepotDo>(depotDos);
    }

    @Override
    public void deleteDepot(Long id) {
        depotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public DepotDTO queryDepotById(Long id) throws ParamException {
        DepotDo depotDo = depotMapper.selectByPrimaryKey(id);
        AssertUtil.isNullObj(depotDo, "未查询到仓库信息");
        return BeanUtil.convertObject(depotDo
                , DepotDTO.class);
    }
}
