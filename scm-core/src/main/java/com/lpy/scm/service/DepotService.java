package com.lpy.scm.service;

import com.github.pagehelper.PageInfo;
import com.lpy.scm.base.service.BaseService;
import com.lpy.scm.dataobject.DepotDo;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.param.AddDepotParam;


/**
 * @author lpy
 * @date 2019/3/26 15:12
 */
public interface DepotService extends BaseService<DepotDo> {
    void addDepot(AddDepotParam addDepotParam);

    PageInfo<DepotDo> getDepots(int pageNo, int pageSize) throws ParamException;
}
