package com.lpy.scm.constants;

/**
 * @author lpy
 * @date 2019/1/24 15:56
 */
public abstract class WebConstants {

    private WebConstants() {}

    /**
     * 路径分隔符
     */
    public static final String SPT = "/";
    /**
     * 索引页
     */
    public static final String INDEX = "index";
    /**
     * 默认模板
     */
    public static final String DEFAULT = "default";
    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 提示信息
     */
    public static final String MESSAGE = "message";
    /**
     * cookie中的JSESSIONID名称
     */
    public static final String JSESSION_COOKIE = "JSESSIONID";
    /**
     * url中的jsessionid名称
     */
    public static final String JSESSION_URL = "jsessionid";
    /**
     * HTTP POST请求
     */
    public static final String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static final String GET = "GET";

    /**
     * 全文检索索引路径
     */
    public static final String LUCENE_PATH = "/WEB-INF/lucene";
    public static final String X_AUTH_MODE = "client_auth";
    public static final String UPLOAD_MODE = "pic";

    /***
     * 与GlobalConstant中定义的值重复，因为codi-base不能引入codi-bus-base
     */
    // Map - Key - Request
    public static final String KEY_REQUEST = "_request";
    // Map - Key - Response
    public static final String KEY_RESPONSE = "_response";
    // Map - Key - API
    public static final String KEY_API = "_api";
}
