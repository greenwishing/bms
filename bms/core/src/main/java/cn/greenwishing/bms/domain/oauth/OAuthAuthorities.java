package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.utils.BmsEnum;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
public enum OAuthAuthorities implements BmsEnum {
    API_BASE("API Base"),
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
