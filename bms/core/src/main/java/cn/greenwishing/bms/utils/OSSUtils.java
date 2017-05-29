package cn.greenwishing.bms.utils;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
public class OSSUtils {

    public static String generateImageUrl(String key) {
        if (ValidationUtils.isEmpty(key)) return "";
        return "http://images.greenwishing.cn/" + key;
    }
}
