package cn.greenwishing.bms.utils.fileupload;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;

/**
 * User: Wu Fan
 */
public class BCSUtils {

    public String upload(BaiduBCS bcs, String bucket, BCSFile file) {
        String key = file.getKey();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getLength());
        bcs.putObject(bucket, key, file.getInputStream(), metadata);
        return key;
    }
}
