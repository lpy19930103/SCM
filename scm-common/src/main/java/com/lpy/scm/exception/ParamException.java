package com.lpy.scm.exception;

/**
 * @author lpy
 * @date 2019/1/25 9:13
 */
public class ParamException extends Exception {

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ParamException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
