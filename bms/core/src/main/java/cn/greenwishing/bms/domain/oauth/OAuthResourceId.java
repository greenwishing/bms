package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.utils.BmsEnum;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public enum OAuthResourceId implements BmsEnum {
    oauth_api("OAuth API");

    private String label;

    OAuthResourceId(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
