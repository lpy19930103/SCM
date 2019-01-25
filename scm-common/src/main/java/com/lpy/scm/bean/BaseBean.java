package com.lpy.scm.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author lpy
 * @date 2019/1/25 10:02
 */
public abstract class BaseBean implements Serializable {

    /**
     * ToStringBuilder – 用于辅助实现Object.toString()方法<p>
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
