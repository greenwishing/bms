package cn.greenwishing.bms.utils.fileupload;

import cn.greenwishing.bms.utils.MD5Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Wu Fan
 */
public class BCSFile {

    private byte[] bytes = new byte[0];
    private String contentType;
    private String extension;

    public byte[] getBytes() {
        return bytes;
    }

    public long getLength() {
        return bytes.length;
    }

    public String getContentType() {
        return contentType;
    }

    public String getExtension() {
        return extension;
    }

    public String getKey() {
        return "/" + MD5Utils.md5(bytes) + "." + extension;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }
}
