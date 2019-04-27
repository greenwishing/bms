package cn.greenwishing.bms.utils;

import cn.greenwishing.bms.cache.ConfigurationCache;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
public class OSSUtils {

    public static String generateImageUrl(String key) {
        if (ValidationUtils.isEmpty(key)) {
            return "";
        }
        String bucket = ConfigurationCache.get("aliyun.oss.bucket", "bmsimgs");
        String endpoint = ConfigurationCache.get("aliyun.oss.endpoint", "oss-cn-shenzhen.aliyuncs.com");
        return "http://" + bucket + "." + endpoint + "/" + key;
    }
}
