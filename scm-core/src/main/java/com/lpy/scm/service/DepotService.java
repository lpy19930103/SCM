package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.DepotDO;
import com.lpy.scm.dto.DepotDTO;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddDepotParam;
import com.lpy.scm.param.EditDepotParam;


/**
 * @author lpy
 * @date 2019/3/26 15:12
 */
public interface DepotService extends BaseService<DepotDO> {
    void addDepot(AddDepotParam addDepotParam);

    void editDepot(EditDepotParam editDepotParam);

    PageInfo<DepotDO> getDepots(int pageNo, int pageSize) throws ParamException;

    void deleteDepot(Long id);

    DepotDTO queryDepotById(Long id) throws ParamException;
}
