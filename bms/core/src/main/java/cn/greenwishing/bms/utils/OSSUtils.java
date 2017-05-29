package cn.greenwishing.bms.utils;

import cn.greenwishing.bms.cache.ConfigurationCache;
import cn.greenwishing.bms.cache.OSSClientFactory;
import com.aliyun.oss.OSSClient;
import org.joda.time.DateTime;

import java.net.URL;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
public class OSSUtils {

    public static String generateImageUrl(String key) {
        if (ValidationUtils.isEmpty(key)) return "";
        String bucketName = ConfigurationCache.get("aliyun.oss.bucketName");
        return generateUrl(bucketName, key);
    }

    public static String generateUrl(String bucketName, String key) {
        DateTime dateTime = new DateTime().plusWeeks(1); // 有效期 1 周
        OSSClient client = OSSClientFactory.getClient();
        URL url = client.generatePresignedUrl(bucketName, key, dateTime.toDate());
        return url.toString();
    }
}
