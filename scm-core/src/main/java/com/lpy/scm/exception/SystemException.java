package com.lpy.scm.exception;

public class SystemException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public SystemException(ErrorEnum errorEnum) {
        super(errorEnum == null ? "" : errorEnum.getMsg());
        if (null == errorEnum) {
            errorEnum = ErrorEnum.SYSTEM_ERROR;
        }
        this.errorCode = errorEnum.getCode() + "";
        this.errorMessage = errorEnum.getMsg();
    }

    public SystemException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}