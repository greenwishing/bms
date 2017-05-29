package cn.greenwishing.bms.cache;

import com.aliyun.oss.OSSClient;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
public class OSSClientFactory {

    private static OSSClient client;

    public static OSSClient getClient() {
        if (client == null) client = new OSSClient(
                ConfigurationCache.get("aliyun.oss.endpoint"),
                ConfigurationCache.get("aliyun.oss.accessKeyId"),
                ConfigurationCache.get("aliyun.oss.accessKeySecret"));
        return client;
    }

    public static void reset() {
        client = null;
    }
}
