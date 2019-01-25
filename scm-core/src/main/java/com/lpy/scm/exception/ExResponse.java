package com.lpy.scm.exception;

/**
 * @author lpy
 * @date 2019/1/24 16:10
 */
public class ExResponse {
    //0代表成功
    Integer code=0;
    String msg;
    Object data;

    public static ExResponse instance(){
        ExResponse res= new ExResponse();
        res.setCode(0);
        res.setMsg("");
        res.setData("");
        return new ExResponse();
    }
    public static ExResponse error(){
        ExResponse res= new ExResponse();
        res.setCode(1);
        res.setMsg("请求失败");
        res.setData("");
        return res;
    }
    public static ExResponse success(){
        ExResponse res= new ExResponse();
        res.setCode(0);
        res.setMsg("请求成功");
        res.setData("");
        return res;
    }



    public Integer getCode() {
        return code;
    }

    public ExResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ExResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ExResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
