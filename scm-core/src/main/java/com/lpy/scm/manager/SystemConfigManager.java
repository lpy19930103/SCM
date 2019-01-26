package com.lpy.scm.manager;

import com.lpy.scm.dataobject.SystemConfigDO;
import com.lpy.scm.enums.system.GlobalIdBizType;

/**
 * @author lpy
 * 全局系统配置配置
 * @date 2019/1/26 10:38
 */
public interface SystemConfigManager {
    void addSystemConfig(SystemConfigDO systemConfigDO);

    void saveSystemConfig(SystemConfigDO systemConfigDO);

    SystemConfigDO findByKey(String key);

    /**
     * 获取下一个全局唯一的ID，共30位
     * 单个表内唯一ID
     * <p>
     * 年月日时分秒：14位，例如：20170920172522
     * 保留位：6位，000000，（分库位置2位、分表位置4位）
     * 表号类型：4位，例如：0123
     * 区域自增id号：6位，例如：123456
     *
     * @param bizType @See GlobalIdBizType 每个数据表表必须要分配一个 GlobalIdBizType
     * @return 全局唯一ID
     */
    String getNextGlobalId(GlobalIdBizType bizType);

    /**
     * 获取系统配置
     *
     * @param masterKey
     * @param subKey
     * @param configKey
     * @return
     */
    SystemConfigDO getSystemConfig(String masterKey, String subKey, String configKey);
}
