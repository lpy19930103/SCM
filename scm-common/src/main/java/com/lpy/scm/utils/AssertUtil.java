package com.lpy.scm.utils;

import com.lpy.scm.exception.BizException;
import com.lpy.scm.exception.ErrorEnum;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lpy
 * @date 2019/1/26 9:57
 */
public class AssertUtil {
    /**
     * @description 一般用于判断一些查询返回map必须不为空的情况 譬如人员信息
     */
    public static void isNullList(List list, String code, String msg) throws ParamException {

        if (list == null || list.size() == 0) {

            throw new ParamException(code, msg);
        }

    }

    /**
     * @description 此方法多用于判断传入参数非空  属于ParameterException
     */
    public static void isNullStr(String str, String code, String msg) throws ParamException {

        if (str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
            throw new ParamException(code, msg);
        }
    }

    public static void isNullStr(String str, String msg) throws ParamException {

        if (str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
            throw new ParamException(ExceptionCode.PARAM_ERROR, msg);
        }
    }

    public static void isNullObj(Object obj, String code, String msg) throws ParamException {
        if (obj == null) {
            throw new ParamException(code, msg);
        }
    }

    public static void isNullObj(Object obj, String msg) throws ParamException {
        if (obj == null) {
            throw new ParamException(ExceptionCode.PARAM_ERROR, msg);
        }
    }

    /**
     * @description 判断业务引起的异常
     */
    public static void isTrueBIZ(Boolean obj, String code, String msg) throws ParamException {
        if (obj) {
            throw new ParamException(code, msg);
        }

    }

    public static void isTrueParam(Boolean obj, String msg) throws ParamException {
        if (obj) {
            throw new ParamException(ExceptionCode.PARAM_ERROR, msg);
        }

    }

    /**
     * @description 判断是否是数字  非数字则抛出异常
     */
    public static void isNumber(String num, String code, String msg) throws ParamException {
        String regEx = "^[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(num);
        if (!mat.find()) {
            throw new ParamException(code, msg);
        }

    }

    /**
     * 空值/空字符串判断
     *
     * @param str
     * @param errorEnum
     */
    public static void isEmpty(String str, ErrorEnum errorEnum) {
        if (!org.springframework.util.StringUtils.isEmpty(str)) {
            return;
        }
        throw new BizException(errorEnum);
    }

    /**
     * 对象是否为空
     *
     * @param object
     * @param errorEnum
     */
    public static void isNull(Object object, ErrorEnum errorEnum) {
        if (!ObjectUtils.isEmpty(object)) {
            return;
        }
        throw new BizException(errorEnum);
    }
}
