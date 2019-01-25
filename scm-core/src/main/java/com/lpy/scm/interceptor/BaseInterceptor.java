package com.lpy.scm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lpy
 * @date 2019/1/24 15:05
 */
public class BaseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return isCrosRequest(httpServletRequest, httpServletResponse);
    }

    /**
     * @description 解决跨域请求的问题
     * 跨域请求科普博客
     * http://www.ruanyifeng.com/blog/2016/04/cors.html
     */
    private boolean isCrosRequest(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        //如果是预检请求 设置下allow的header参数 OPTIONS为预检请求
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            //鉴于header中存放了很多业务相关的信息 直接用*匹配
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Request-Headers", "*");
            response.setHeader("Access-Control-Max-Age", "3600");//30min
            flag = true;
        } else {
            //   允许跨域，允许复杂请求  也可以使用,号隔开的n个域名指定死某些域名允许访问
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
