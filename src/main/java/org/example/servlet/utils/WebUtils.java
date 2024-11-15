package org.example.servlet.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static int parseInt(String str, int defaultValue) {

        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        str = str.trim();
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
