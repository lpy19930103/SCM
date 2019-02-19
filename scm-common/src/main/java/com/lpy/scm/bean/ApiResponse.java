package com.lpy.scm.bean;

/**
 * @author lpy
 * @date 2019/1/24 16:10
 */
public class ApiResponse<T> extends BaseBean {
    //0代表成功
    private Integer code = 0;
    private String msg;
    private T data;
    private Integer total;


    public static <T> ApiResponse instance() {
        ApiResponse<T> res = new ApiResponse<>();
        res.setCode(0);
        res.setMsg("");
//        res.setData("");
        return new ApiResponse();
    }

    public static <T> ApiResponse error() {
        ApiResponse<T> res = new ApiResponse<>();
        res.setCode(1);
        res.setMsg("请求失败");
//        res.setData("");
        return res;
    }

    public static <T> ApiResponse success() {
        ApiResponse<T> res = new ApiResponse<>();
        res.setCode(0);
        res.setMsg("请求成功");
//        res.setData("");
        return res;
    }

    public Integer getTotal() {
        return total;
    }

    public ApiResponse setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ApiResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ApiResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
