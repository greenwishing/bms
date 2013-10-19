package cn.greenwishing.bms.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author Wu Fan
 */
public class StringDecoder {

    /**
     * 处理请求为get方式时中文乱码的问题
     *
     * @param value 传入的字符串
     * @return 处理后的字符串
     */
    public static String decode(String value) {
        if (value == null) {
            return null;
        }
        try {
            return new String(value.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
