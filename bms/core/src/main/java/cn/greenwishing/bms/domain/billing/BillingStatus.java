package cn.greenwishing.bms.domain.billing;

/**
 * @author Frank wu
 * @date 2016/6/27
 */
public enum BillingStatus {
    NORMAL(""),
    DESTROYED("已作废"),

    @Deprecated
    RECEIVABLE("未还"),
    @Deprecated
    RECEIVED("已还"),

    @Deprecated
    PAYABLE("未付"),
    @Deprecated
    PAYED("已付"),
    ;

    private String label;

    BillingStatus(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
