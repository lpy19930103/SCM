package com.lpy.scm.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 辅助类
 */
public class CookieUtils {

    private CookieUtils() {}

    /**
     * 每页条数cookie名称
     */
    public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
    /**
     * 默认每页条数
     */
    public static final int DEFAULT_SIZE = 20;
    /**
     * 最大每页条数
     */
    public static final int MAX_SIZE = 200;


    /**
     * 获得cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie name
     * @return if exist return cookie, else return null.
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieStr(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null) {
            return null;
        }

        return cookie.getValue();
    }

    /**
     * 添加会话级cookie
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        return addCookie(request, response, name, value, null, null);
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
                                   Integer expiry, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     *
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
