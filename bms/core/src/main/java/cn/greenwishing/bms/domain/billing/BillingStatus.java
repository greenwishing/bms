package cn.greenwishing.bms.domain.billing;

/**
 * User: Wufan
 * Date: 2016/6/27
 */
public enum BillingStatus {
    NORMAL(""),

    RECEIVABLE("未还"),
    RECEIVED("已还"),

    PAYABLE("未付"),
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
