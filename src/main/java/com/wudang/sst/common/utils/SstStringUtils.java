package com.wudang.sst.common.utils;

import org.springframework.util.StringUtils;

/**
 * 字符串处理类
 *
 * @author jet
 * @date 23-11-2019
 */
public class SstStringUtils extends StringUtils {

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
}
