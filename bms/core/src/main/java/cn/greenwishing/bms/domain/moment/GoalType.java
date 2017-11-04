package cn.greenwishing.bms.domain.moment;

/**
 * @author Frank wu
 * @date 2017/3/4
 */
public enum GoalType {
    NONE("无"),
    DAILY("每日"),
    WEEKLY("每周"),
    ;

    private String label;

    GoalType(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
