package com.hlw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: BookGrab
 * @description:HTML标签过滤工具类过滤所有以"<"开头以">"结尾的标签
 * @author: houlongwang
 * @create: 2019-05-02 00:37
 **/
public class HtmlFilterUtil {
        private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

        /**
         * 过滤HTML标签：过滤所有以"<"开头以">"结尾的标签
         *
         * @param str 需要过滤的字符串
         * @return String   过滤后的字符串
         */
        public static String filterHtml(String str) {
            Pattern pattern = Pattern.compile(regxpForHtml);
            Matcher matcher = pattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            boolean result1 = matcher.find();
            while (result1) {
                matcher.appendReplacement(sb, "");
                result1 = matcher.find();
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
}


