package com.lpy.scm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author lpy
 * @date 2019/1/24 15:50
 */
public abstract class MethodLogAspect {

    private Logger logger = LoggerFactory.getLogger(MethodLogAspect.class);

    /***
     * 记录业务日志切面
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        long startTime = System.nanoTime();
        Object result = null;
        result = joinPoint.proceed(args);
        long endTime = System.nanoTime();
        try {
            String logString = extractLog(joinPointObject, result, args, methodName, startTime, endTime);
            Logger customLogger = getLogger();
            if (customLogger == null) {
                customLogger = logger;
            }
            customLogger.info(logString);
        } catch (Exception exception) {
            logger.error("MethodLogAspect.around", exception);
        }
        return result;
    }

    protected abstract Logger getLogger();

    protected abstract String extractLog(MethodSignature joinPointObject, Object result, Object[] args, String methodName, long startTime, long endTime);

    /**
     * 获取完整的日志
     *
     * @param request
     * @param error
     * @param exception
     */
    protected abstract String extractLog(HttpServletRequest request, String error, Exception exception);


    /**
     * 获取时间，单位：微妙 μs
     *
     * @param startTime 单位：纳秒
     * @param endTime   单位：纳秒
     * @return
     */
    protected String getCostTime(long startTime, long endTime) {
        return (endTime - startTime) / 1000 + "μs";
    }


    /**
     * 给没被切面捕获的方法提供日志入口，目前只有方法抛出异常的时候才会跳出切面
     *
     * @param request
     * @param error
     * @param outException
     */
    public void logProxy(HttpServletRequest request, String error, Exception outException) {
        try {
            String logString = extractLog(request, error, outException);
            Logger customLogger = getLogger();
            if (customLogger == null) {
                customLogger = logger;
            }
            customLogger.info(logString);
        } catch (Exception exception) {
            logger.error("MethodLogAspect.around", exception);
        }
    }
}