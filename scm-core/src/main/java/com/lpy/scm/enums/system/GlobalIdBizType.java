package com.lpy.scm.enums.system;

/**
 * @author lpy
 * @date 2019/1/26 10:54
 * @description 全局id生成器 入参 需要的 枚举类型
 * 原则上命名原则：数据库名_表名
 */
public enum GlobalIdBizType {

    SCM_USER("scm", "sys_user", "0001"),
    SCM_CATEGORY("scm", "base_category", "0002");


    private String dbName;
    private String tableName;
    private String value;
    private String describe;

    GlobalIdBizType(String dbName, String tableName, String value) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.value = value;
    }

    GlobalIdBizType(String dbName, String tableName, String value, String describe) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.value = value;
        this.describe = describe;
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getValue() {
        return value;
    }

}
