package cn.greenwishing.bms.dto;

import cn.greenwishing.bms.utils.OSSUtils;

/**
 * @author Frank wu
 * @date 2017/5/29
 */
public class OSSKey {

    private String key;

    public OSSKey() {
    }

    public OSSKey(String key) {
        this.key = key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public String getUrl() {
        return OSSUtils.generateImageUrl(key);
    }

    @Override
    public String toString() {
        return getKey();
    }
}
