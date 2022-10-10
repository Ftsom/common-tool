package com.ftsom.common.utils;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * 基于「google Converter」的驼峰和下划线转换工具类
 *
 * @author ftsom
 * @date 2022/5/23 17:05
 */
public class CamelUnderlineUtil {
    private static final Converter<String, String> CONVERTER1 = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);
    private static final Converter<String, String> CONVERTER2 = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);


    public static String underlineToCamel(String value) {
        return CONVERTER1.convert(value);
    }

    public static String camelToUnderlineTo(String value) {
        return CONVERTER2.convert(value);
    }

}
