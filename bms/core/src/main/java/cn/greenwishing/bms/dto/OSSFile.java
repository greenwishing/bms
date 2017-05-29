package cn.greenwishing.bms.dto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
public class OSSFile {

    private String name;
    private String originalFilename;
    private String contentType;
    private byte[] bytes;

    public OSSFile(String name, String contentType, byte[] bytes) {
        this(name, name, contentType, bytes);
    }

    public OSSFile(String name, String originalFilename, String contentType, byte[] bytes) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }

    public InputStream getInputStream() {
        if (isEmpty()) return null;
        return new ByteArrayInputStream(bytes);
    }
}
