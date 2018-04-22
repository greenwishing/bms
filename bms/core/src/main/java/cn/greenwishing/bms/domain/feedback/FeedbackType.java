package cn.greenwishing.bms.domain.feedback;

/**
 * @author Wufan
 * @date 2018/4/21
 */
public enum FeedbackType {
    ACCOUNT("帐号"),
    FUNCTION("功能"),
    UI("界面"),
    UE("体验"),
    OTHER("其它"),
    ;
    private String label;

    FeedbackType(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
