package cn.greenwishing.bms.domain.billing;

/**
 * 账户类型
 *
 * User: Wufan
 * Date: 2016/10/15
 */
public enum BillingAccountType {
    CASH("现金账户"),
    CREDIT_CARD("信用卡账户"),
    DEPOSIT_CARD("储蓄卡账户"),
    PREPAY_CARD("储值卡账户"),
    VIRTUAL("虚拟账户"),
    INDEBTED("负债账户"),
    LOAN("债权账户"),
    INVEST("投资账户"),
    INSURANCE("保险账户"),
    ;

    private String label;

    BillingAccountType(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
