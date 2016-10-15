package cn.greenwishing.bms.domain.user;

/**
 * User: Wu Fan
 */
public enum UserStatus {
    ENABLED("已启用"),
    EXPIRED("已过期"),
    LOCKED("已锁定"),
    DISABLED("已禁用");

    private String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
