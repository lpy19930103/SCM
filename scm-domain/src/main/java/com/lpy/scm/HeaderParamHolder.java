package com.lpy.scm;

import com.lpy.scm.param.HeaderParam;
import org.springframework.core.NamedThreadLocal;

/**
 * headerinfo的信息holder类
 */
public abstract class HeaderParamHolder {

    private static final ThreadLocal<HeaderParam> headerInfoThreadLocalHolder = new NamedThreadLocal("headinfo");

    public HeaderParamHolder() {
    }

    public static void setHeaderInfo(HeaderParam info) {
        headerInfoThreadLocalHolder.set(info);
    }

    public static HeaderParam getHeaderInfo() {
        return headerInfoThreadLocalHolder.get();
    }

    public static String getToken() {
        return headerInfoThreadLocalHolder.get().getToken();
    }

    public static String getDeviceId() {
        return headerInfoThreadLocalHolder.get().getDeviceId();
    }

    public static String getUserId() {
        return headerInfoThreadLocalHolder.get().getUserId();
    }

    public static String getClientType() {
        return headerInfoThreadLocalHolder.get().getClientType();
    }

    public static String getPhoneVersion() {
        return headerInfoThreadLocalHolder.get().getPhoneVersion();
    }

    public static String getSign() {
        return headerInfoThreadLocalHolder.get().getSign();
    }

    public static String getTime() {
        return headerInfoThreadLocalHolder.get().getTime();
    }

    public static String getSoftVersion() {
        return headerInfoThreadLocalHolder.get().getSoftVersion();
    }

}

