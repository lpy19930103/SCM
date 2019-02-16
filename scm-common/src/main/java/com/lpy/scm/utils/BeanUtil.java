package com.lpy.scm.utils;

import com.lpy.scm.exception.BizException;
import com.lpy.scm.exception.ErrorEnum;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author lpy
 * @date 2019/1/26 9:46
 * @description bean工具类
 */
public class BeanUtil {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;

        T obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        return obj;
    }

    public static <T> T convertObject(Object sourceObj, Class<T> targetClz) {
        if (sourceObj == null) {
            return null;
        }
        if (targetClz == null) {
            throw new IllegalArgumentException("parameter clz not be null");
        }
        try {
            Object targetObj = targetClz.newInstance();
            BeanUtils.copyProperties(sourceObj, targetObj);
            return (T) targetObj;
        } catch (Exception e) {
            throw new BizException(ErrorEnum.TYPE_CONVERSION);
        }
    }
}
