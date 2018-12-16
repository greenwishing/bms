package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.cache.ConfigurationCache;
import cn.greenwishing.bms.dto.OSSFile;
import cn.greenwishing.bms.oss.OSSClientFactory;
import cn.greenwishing.bms.service.OSSService;
import cn.greenwishing.bms.utils.BmsClient;
import cn.greenwishing.bms.utils.MD5Utils;
import cn.greenwishing.bms.utils.ValidationUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
@Service("ossService")
public class OSSServiceImpl implements OSSService {

    @Override
    public String upload(OSSFile file, String mode) {
        if (file.isEmpty()) {
            return null;
        }

        OSSClient client = OSSClientFactory.getClient();
        String bucketName = ConfigurationCache.get("aliyun.oss.bucketName");
        if (!client.doesBucketExist(bucketName)) {
            client.createBucket(bucketName);
        }

        byte[] bytes = file.getBytes();
        String contentMd5 = MD5Utils.md5(bytes);
        String key = contentMd5 + suffix(file);

        if (!client.doesObjectExist(bucketName, key)) {
            // 文件不存在就上传
            String contentType = file.getContentType();
            if (ValidationUtils.isEmpty(contentType)) {
                contentType = genContentType(file);
            }

            // metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bytes.length);
            // metadata.setContentMD5(contentMd5); // InvalidDigest: The Content-MD5 you specified was invalid.
            metadata.setContentType(contentType);
            metadata.addUserMetadata("original-filename", file.getOriginalFilename());

            client.putObject(bucketName, key, file.getInputStream(), metadata);
        }
        return key;
    }

    @Override
    public String upload(String avatarUrl) {
        byte[] bytes;
        InputStream is = null;
        try {
            URL url = new URL(avatarUrl);
            is = url.openStream();
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            return avatarUrl;
        } finally {
            IOUtils.closeQuietly(is);
        }
        OSSFile file = new OSSFile("wx_avatar", "avatar.jpg", "image/jpeg", bytes);
        return upload(file, "image");
    }

    public static String suffix(OSSFile file) {
        String originalFilename = file.getOriginalFilename();
        return originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
    }

    public static String genContentType(OSSFile file) {
        String k = suffix(file);
        if (k.endsWith(".jpg") || k.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (k.endsWith(".png")) {
            return "image/png";
        } else if (k.endsWith(".gif")) {
            return "image/gif";
        } else {
            return null;
        }
    }
}
