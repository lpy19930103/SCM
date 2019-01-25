package com.lpy.scm;

/**
 * @author lpy
 * @date 2019/1/24 17:44
 */
public abstract class BaseLockSign {

    /**
     * @description 获取实际本地变量类型名称
     */
    public abstract String getLockLocalName();

    @Override
    public String toString() {
        return getLockLocalName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * @description 设置本地线程对应key的redis分布式锁上锁标识
     */
    public abstract void setLockSign(boolean lockSign);


    /**
     * @description 获取本地线程对应key的redis分布式锁上锁标识
     */
    public abstract boolean getLockSign();


    /**
     * @description
     */
    public abstract void clear();


}
