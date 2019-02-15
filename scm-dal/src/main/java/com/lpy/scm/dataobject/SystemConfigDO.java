package com.lpy.scm.dataobject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/2/15 16:14
 * @description 系统配置
 */
@Table(name = "sys_config")
public class SystemConfigDO {
    /**
     * 系统配置ID
     */
    @Id
    @Column(name = "system_config_id")
    private String systemConfigId;

    /**
     * 主Key
     */
    @Column(name = "master_key")
    private String masterKey;

    /**
     * 子Key
     */
    @Column(name = "sub_key")
    private String subKey;

    /**
     * Key
     */
    @Column(name = "config_key")
    private String configKey;

    /**
     * Key描述
     */
    @Column(name = "key_description")
    private String keyDescription;

    /**
     * 值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 是否需要加入redis缓存
     */
    @Column(name = "need_cache")
    private String needCache;

    @Column
    private String creater;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "update_at")
    private Date updateAt;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * 获取系统配置ID
     *
     * @return system_config_id - 系统配置ID
     */
    public String getSystemConfigId() {
        return systemConfigId;
    }

    /**
     * 设置系统配置ID
     *
     * @param systemConfigId 系统配置ID
     */
    public void setSystemConfigId(String systemConfigId) {
        this.systemConfigId = systemConfigId;
    }

    /**
     * 获取主Key
     *
     * @return master_key - 主Key
     */
    public String getMasterKey() {
        return masterKey;
    }

    /**
     * 设置主Key
     *
     * @param masterKey 主Key
     */
    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    /**
     * 获取子Key
     *
     * @return sub_key - 子Key
     */
    public String getSubKey() {
        return subKey;
    }

    /**
     * 设置子Key
     *
     * @param subKey 子Key
     */
    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

    /**
     * 获取Key
     *
     * @return config_key - Key
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * 设置Key
     *
     * @param configKey Key
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * 获取Key描述
     *
     * @return key_description - Key描述
     */
    public String getKeyDescription() {
        return keyDescription;
    }

    /**
     * 设置Key描述
     *
     * @param keyDescription Key描述
     */
    public void setKeyDescription(String keyDescription) {
        this.keyDescription = keyDescription;
    }

    /**
     * 获取值
     *
     * @return config_value - 值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 设置值
     *
     * @param configValue 值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 获取是否需要加入redis缓存
     *
     * @return need_cache - 是否需要加入redis缓存
     */
    public String getNeedCache() {
        return needCache;
    }

    /**
     * 设置是否需要加入redis缓存
     *
     * @param needCache 是否需要加入redis缓存
     */
    public void setNeedCache(String needCache) {
        this.needCache = needCache;
    }


}