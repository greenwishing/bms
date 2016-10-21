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
    VIRTUAL("虚拟账户"),
    INDEBTED("负债账户", true),
    LOAN("债权账户", true),
    ;

    private String label;
    private boolean loan;

    BillingAccountType(String label) {
        this.label = label;
    }

    BillingAccountType(String label, boolean loan) {
        this.label = label;
        this.loan = loan;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }

    public boolean isLoan() {
        return loan;
    }
}
