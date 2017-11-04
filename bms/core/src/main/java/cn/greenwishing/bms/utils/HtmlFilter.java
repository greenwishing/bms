package cn.greenwishing.bms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Frank wu
 * @date 13-7-6
 */
public class HtmlFilter {
    private static final String REGEX_HTML = "<[^>]+>|<[^>]+";

    private static String doFilter(String content, String regex, String value) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        return matcher.replaceAll(value);
    }

    public static String doFilter(String content) {
        if (content == null) {
            return null;
        }
        content = doFilter(content, REGEX_HTML, " ");
        content = content.replaceAll("\r\n", " ");
        return content;
    }
}
