package com.wudang.wdt.common.utils;

import org.springframework.util.StringUtils;

/**
 * 字符串处理类
 *
 * @author jet
 * @date 23-11-2019
 */
public class WdtStringUtils extends StringUtils {

    /**
     * 替换最后一个字符
     *
     * @param string
     * @param toReplace
     * @param replacement
     * @return
     */
    public static String replaceLast(String string, String toReplace, String replacement) {
        if (string == null) {
            return "";
        }
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length());
        } else {
            return string;
        }
    }

    /**
     * {@literal https://blog.csdn.net/zouxucong/article/details/71081853}
     *
     * @param value
     * @return
     */
    public static int wordLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
