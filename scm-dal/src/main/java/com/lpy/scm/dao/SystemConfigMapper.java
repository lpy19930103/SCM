package com.lpy.scm.dao;

import com.lpy.scm.dataobject.SystemConfigDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SystemConfigMapper extends Mapper<SystemConfigDO> {

    /**
     * 通过 @see GlobalIdBizType 查询对应数据库表当前可分配的随机位id
     * 采用了悲观锁机制，请勿随意调用
     *
     * @param dbName    对应表结构的 master key
     * @param tableName 对应表结构的 sub key
     * @param bizType   对应表结构的 key
     * @return 查询到的唯一对象
     * @auth GarryKing
     * @Date 2017年09月22日11:31:31
     */
    SystemConfigDO selectGlobalIdConfigForUpdate(Map<String, String> map);
}