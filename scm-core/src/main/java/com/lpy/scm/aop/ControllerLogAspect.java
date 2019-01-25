package com.lpy.scm.aop;

import com.alibaba.fastjson.JSON;
import com.lpy.scm.constants.GlobalConstant;
import com.lpy.scm.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lpy
 * @date 2019/1/24 16:02
 */
@Component
public class ControllerLogAspect extends MethodLogAspect {

    @Override
    public String extractLog(MethodSignature joinPointObject, Object result, Object[] args, String methodName, long startTime,
                             long endTime) {
        // 获取入参值
        Map<String, String> requestMap = null;
        Map<String, String> requestHeaders = null;
        String url = "";

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            requestMap = RequestUtils.getRequestMap(request);
            requestHeaders = RequestUtils.getQueryHeaders(request);
            url = RequestUtils.getLocation(request);
        } catch (Exception ex) {
            getLogger().error("获取HttpServletRequest出错", ex);
        }

        return getLogMap(result, methodName, url, requestMap, requestHeaders, startTime, endTime);
    }

    /**
     * 给没被切面捕获的方法提供日志入口，目前只有方法抛出异常的时候才会跳出切面
     *
     * @param request
     * @param error
     * @param exception
     */
    @Override
    protected String extractLog(HttpServletRequest request, String error, Exception exception) {
        // 获取入参值
        Map<String, String> requestMap = null;
        Map<String, String> requestHeaders = null;
        String url = "";

        try {
            requestMap = RequestUtils.getRequestMap(request);
            requestHeaders = RequestUtils.getQueryHeaders(request);
            url = RequestUtils.getLocation(request);
        } catch (Exception ex) {
            getLogger().error("获取HttpServletRequest出错", ex);
        }

        Object result = null;
        if (exception != null) {
            String message = StringUtils.isEmpty(error) ? "-" : error + "-" + exception.getMessage() + "-" + ExceptionUtils.getStackTrace(exception);
            result = message;
        } else {
            result = error;
        }

        return getLogMap(result, "", url, requestMap, requestHeaders, 0, 0);
    }

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private String getLogMap(Object response, String methodName, String url, Object request, Object requestHeaders,
                             long startTime, long endTime) {
        // 用于记录请求日志
        Map<String, Object> map = new HashMap<>();
        // 执行API接口所耗费的时间
        map.put(GlobalConstant.KEY_API_COST_TIME, getCostTime(startTime, endTime));
        // 请求的URL
        map.put(GlobalConstant.KEY_API, url);
        // 请求的方法
        map.put(GlobalConstant.KEY_METHOD, methodName);
        // 请求的参数
        map.put(GlobalConstant.KEY_REQUEST, request);
        // 请求头
        map.put(GlobalConstant.KEY_REQUEST_HEADER, requestHeaders);
        // 返回的结果
        map.put(GlobalConstant.KEY_RESPONSE, response);

        return JSON.toJSONString(map);
    }
}
