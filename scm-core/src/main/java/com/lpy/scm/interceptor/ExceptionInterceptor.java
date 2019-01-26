package com.lpy.scm.interceptor;

import com.alibaba.fastjson.JSON;
import com.lpy.scm.HeaderParamHolder;
import com.lpy.scm.aop.ControllerLogAspect;
import com.lpy.scm.bean.ApiResponse;
import com.lpy.scm.exception.*;
import com.lpy.scm.param.HeaderParam;
import com.lpy.scm.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author lpy
 * @date 2019/1/24 15:40
 */
public class ExceptionInterceptor extends BaseInterceptor {

    private static Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Map headerInfoMap = new HashMap();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headerInfoMap.put(key, value);
        }
        HeaderParam info = new HeaderParam();
        try {
            info = BeanUtil.mapToObject(headerInfoMap, HeaderParam.class);
            HeaderParamHolder.setHeaderInfo(info);
        } catch (Exception e) {
            log.error("Header map transfer to bean error!", e);
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ApiResponse apiResponse = ApiResponse.error();
        if (ex != null) {
            // 记录输入输出日志, 该部分日志跳出了切面日志捕获 
            ControllerLogAspect aspect = new ControllerLogAspect();
            if (null == ex.getMessage()) {
                log.error(ex.toString());
            } else {
                log.error(ex.getMessage());
            }
            if (ex instanceof BizException) {
                BizException exception = (BizException) ex;
                ErrorEnum errorEnum = exception.getErrorEnum();
                if (errorEnum != null) {
                    apiResponse.setCode(errorEnum.getCode()).setMsg(errorEnum.getMsg());
                } else {
                    apiResponse.setCode(Integer.valueOf(exception.getErrorCode())).setMsg(exception.getErrorMessage());
                }
                if (exception.getData() != null) {
                    apiResponse.setData(exception.getData());
                }
                // 记录输入输出日志, 该部分日志跳出了切面日志捕获 
                aspect.logProxy(request, apiResponse.getMsg(), null);
            } else if (ex instanceof SystemException) {
                String errorCode = ((SystemException) ex).getErrorCode();
                String errorMsg = ((SystemException) ex).getErrorMessage();
                apiResponse.setCode(Integer.valueOf(errorCode)).setMsg(errorMsg);

                // 记录输入输出日志, 该部分日志跳出了切面日志捕获 
                aspect.logProxy(request, apiResponse.getMsg(), null);
            } else if (ex instanceof ParamException) {
                String errorCode = ((ParamException) ex).getErrorCode();
                String errorMsg = ((ParamException) ex).getErrorMessage();
                apiResponse.setCode(Integer.valueOf(errorCode)).setMsg(errorMsg);

                // 记录输入输出日志, 该部分日志跳出了切面日志捕获 
                aspect.logProxy(request, apiResponse.getMsg(), null);
            } else {
                log.error(ex.getMessage(), ex);
                //-----------------------
                ex.printStackTrace();
                ErrorEnum errorEnum = ErrorEnum.SYSTEM_ERROR;
                apiResponse.setCode(errorEnum.getCode()).setMsg(errorEnum.getMsg());

                // 记录输入输出日志, 该部分日志跳出了切面日志捕获 - add by Shangdu Lin - 20171013
                aspect.logProxy(request, apiResponse.getMsg(), ex);
            }
            String json = JSON.toJSONString(apiResponse);
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }
    }
}