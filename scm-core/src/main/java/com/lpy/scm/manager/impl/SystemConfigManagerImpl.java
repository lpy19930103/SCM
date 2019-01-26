package com.lpy.scm.manager.impl;

import com.lpy.scm.constants.SystemConstants;
import com.lpy.scm.dao.SystemConfigMapper;
import com.lpy.scm.dataobject.SystemConfigDO;
import com.lpy.scm.enums.system.GlobalIdBizType;
import com.lpy.scm.exception.ErrorEnum;
import com.lpy.scm.exception.SystemException;
import com.lpy.scm.manager.SystemConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lpy
 * @date 2019/1/26 11:00
 */
@Component
public class SystemConfigManagerImpl implements SystemConfigManager {

    /**
     * 此tomcat容器，下一个可以分配的全局唯一ID
     */
    private AtomicLong currId = null;

    /**
     * 此tomcat容器，最后一个可以分配的全局唯一ID
     */
    private AtomicLong endId = null;

    /**
     * 此tomcat容器，id生成器专用锁
     */
    private Lock idLock = new ReentrantLock();// 锁对象


    private long currEndId = 0;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addSystemConfig(SystemConfigDO systemConfigDO) {
        systemConfigMapper.insert(systemConfigDO);
    }

    @Override
    public void saveSystemConfig(SystemConfigDO systemConfigDO) {
        systemConfigMapper.updateByPrimaryKeySelective(systemConfigDO);
    }

    @Override
    public SystemConfigDO findByKey(String key) {
        SystemConfigDO queryDO = new SystemConfigDO();
        queryDO.setConfigKey(key);
        return systemConfigMapper.selectOne(queryDO);
    }

    @Override
    public String getNextGlobalId(GlobalIdBizType bizType) {
        idLock.lock();
        String result = "";
        try {
            if (currId == null || currId.longValue() < 0 || endId == null || endId.longValue() <= 0) {
                refreshNewId(bizType);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date now = new Date(System.currentTimeMillis());
            result += sdf.format(now);
            result += SystemConstants.GLOBAL_ID_STEP_RESERVED;
            result += bizType.getValue();
            result += turnSuffixToString(currId.longValue());
            refreshNewId(bizType);
        } finally {
            idLock.unlock();
        }
        return result;
    }

    @Override
    public SystemConfigDO getSystemConfig(String masterKey, String subKey, String configKey) {
        SystemConfigDO system = new SystemConfigDO();
        system.setMasterKey(masterKey);
        system.setSubKey(subKey);
        system.setConfigKey(configKey);
        SystemConfigDO systemConfigDO = systemConfigMapper.selectOne(system);
        return systemConfigDO;
    }


    /**
     * 请勿随意调用，专门提供给 getNextGlobalId 方法使用
     *
     * @param bizType
     * @throws SystemException
     */
    private void refreshNewId(GlobalIdBizType bizType) {
        if (currId == null || currId.longValue() < 0 || endId == null || endId.longValue() <= 0) {
            refreshNewIdFromDB(bizType);
        }
        if (currId.longValue() < endId.longValue()) {
            currId.incrementAndGet();
        } else if (currId.longValue() == endId.longValue()) {
            refreshNewIdFromDB(bizType);
        } else {
            throw new SystemException(ErrorEnum.SYSTEM_ERROR);
        }
    }

    private String turnSuffixToString(long target) {
        long bigBoy = 1000000;
        String temp = (bigBoy + target) + "";
        return temp.substring(1);
    }


    /**
     * 请勿随意调用，专门提供给 getNextGlobalId 方法使用
     *
     * @param bizType
     */
    @Transactional
    public void refreshNewIdFromDB(GlobalIdBizType bizType) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("dbName", SystemConstants.PREFIX_SYSTEM_ID + bizType.getDbName());
        queryMap.put("tableName", bizType.getTableName());
        queryMap.put("bizType", bizType.getValue());
        SystemConfigDO currConfig = systemConfigMapper.selectGlobalIdConfigForUpdate(queryMap);
        if (currConfig == null) {
            throw new SystemException(ErrorEnum.STEP_CONFIG_NOT_EXIST);
        }
        long currLong = Long.parseLong(currConfig.getConfigValue());
        long nextLong = currLong + SystemConstants.GLOBAL_ID_STEP_LENGTH;
        if (currLong < 0) {
            throw new SystemException(ErrorEnum.STEP_CONFIG_NOT_EXIST);
        }
        if (currId != null && currId.longValue() == currLong) {
            throw new SystemException(ErrorEnum.STEP_DB_INCREASE_ERROR);
        }
        currConfig.setConfigValue(turnSuffixToString((nextLong)));
        systemConfigMapper.updateByPrimaryKey(currConfig);
        currId = new AtomicLong(currLong);
        endId = new AtomicLong(nextLong - 1);
    }

}
