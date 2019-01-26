package com.lpy.scm.exception;

public enum ErrorEnum {
    // 系统
    SYSTEM_ERROR(999999, "系统异常"),
    SMS_ERROR(900001, "短信发送异常"),
    APP_NOT_EXIST(900002, "未找到APP"),
    APP_IDENTIFIER_NOT_EXIST(900003, "为了您的产品体验，请去官网下载最新版本 !"),
    APP_VERSION_NEED_UPDATE(900004, "为了您的产品体验，请升级至最新版本 !"),
    SYSTEM_BUSY(900005, "当前系统繁忙,请稍后再试!"),
    ;


    private int code;
    private String message;
    private Object[] params;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorEnum(int code, String message, Object[] params) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

}