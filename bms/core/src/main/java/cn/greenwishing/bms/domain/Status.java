package cn.greenwishing.bms.domain;

/**
 * @author Frank wu
 * @date 2017/3/4
 */
public enum Status {
    NORMAL(""),
    DISABLED("已禁用"),
    REMOVED("已删除"),
    ;

    private String label;

    Status(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
