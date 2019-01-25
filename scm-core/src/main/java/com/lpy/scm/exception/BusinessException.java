package com.lpy.scm.exception;


/**
 * @author lpy
 * @date 2019/1/25 9:13
 */
public class BusinessException extends RuntimeException {

    private ErrorEnum error;
    private String msg;
    private Object data;
    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorEnum getErrorEnum() {
        return error;
    }


    public BusinessException(ErrorEnum error) {
        super(error == null ? "" : error.getMsg());
        this.error = error;
    }

    public BusinessException(ErrorEnum error, Object data) {
        super(error == null ? "" : error.getMsg());
        this.error = error;
        this.data = data;
    }

    public BusinessException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.errorCode = ExceptionCode.BIZ_ERROR;
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}