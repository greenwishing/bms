package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.utils.BmsEnum;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public enum OAuthGrantType implements BmsEnum {
    password("Password"),
    authorization_code("Authorization code"),
    refresh_token("Refresh token"),
    implicit("Implicit"),
    client_credentials("Client credentials");

    private String label;

    OAuthGrantType(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
