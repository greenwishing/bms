package cn.greenwishing.bms.utils;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
public class OSSUtils {

    public static String generateImageUrl(String key) {
        if (ValidationUtils.isEmpty(key)) return "";
        return "http://images.greenwishing.cn/" + key;
    }
}
