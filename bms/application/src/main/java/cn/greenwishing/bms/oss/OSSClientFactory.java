package cn.greenwishing.bms.oss;

import cn.greenwishing.bms.cache.ConfigurationCache;
import com.aliyun.oss.OSSClient;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
public class OSSClientFactory {

    private static OSSClient client;

    public static OSSClient getClient() {
        if (client == null) {
            String endpoint = ConfigurationCache.get("aliyun.oss.endpoint");
            String accessKeyId = ConfigurationCache.get("aliyun.oss.accessKeyId");
            String secretAccessKey = ConfigurationCache.get("aliyun.oss.accessKeySecret");
            client = new OSSClient(endpoint, accessKeyId, secretAccessKey);
        }
        return client;
    }

    public static void reset() {
        client.shutdown();
        client = null;
    }
}
