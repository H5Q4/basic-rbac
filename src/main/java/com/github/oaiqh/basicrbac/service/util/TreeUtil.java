package com.github.oaiqh.basicrbac.service.util;


import org.apache.commons.lang3.StringUtils;

public class TreeUtil {
    public static final String SEPARATOR = "-";
    public static final String ROOT = "0";

    public static String calculateLevel(String parentLevel, Long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
