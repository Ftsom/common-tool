package com.ftsom.common.utils;

import com.google.common.base.Joiner;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

import static com.ftsom.common.constants.NumConstant.INT_1;

/**
 * 基于「guava Joiner」的单词连接工具类
 *
 * @author ftsom
 * @date 2022/6/2 10:02
 */
public class JoinerUtil {

    public static String toStr(String... items) {
        return toStrBySeparator(StringUtils.EMPTY, items);
    }

    public static String toStrBySeparator(String separator, Collection<?> strList) {
        if (CollectionUtils.isEmpty(strList)) {
            return StringUtils.EMPTY;
        }
        return Joiner.on(separator).join(strList);
    }

    public static String toStrBySeparator(String separator, String... items) {
        if (items.length < INT_1) {
            return StringUtils.EMPTY;
        }
        return Joiner.on(separator).join(items);
    }
}
