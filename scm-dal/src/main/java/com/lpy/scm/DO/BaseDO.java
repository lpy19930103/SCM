package com.lpy.scm.DO;

import java.util.Date;

/**
 * @author lpy
 * @date 2019/1/26 10:28
 */
public class BaseDO {
    private String creater;
    private Date create_at;
    private Date update_at;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
