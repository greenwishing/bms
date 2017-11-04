package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.utils.BmsEnum;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public enum OAuthScope implements BmsEnum {
    read("Read"),
    write("Write"),
    trust("Trust");

    public String label;

    OAuthScope(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
