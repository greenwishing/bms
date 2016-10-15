package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.utils.BmsEnum;

/**
 * User: Wufan
 * Date: 2015/11/7.
 */
public enum OAuthAuthorities implements BmsEnum {
    ROLE_API_BASE("API Base"),
    ;

    private String label;

    OAuthAuthorities(String label) {
        this.label = label;
    }

    @Override
    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
