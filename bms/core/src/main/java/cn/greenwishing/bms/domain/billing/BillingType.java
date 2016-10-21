package cn.greenwishing.bms.domain.billing;

import java.util.Arrays;
import java.util.List;

public enum BillingType {
    EXPEND("支出"),
    INCOME("收入"),
    TRANSFER("转账"),
    BORROW("借入"),
    LOAN("借出/代付"),
    RECEIVE("收款"),
    PAYBACK("还款"),
    ;

	private String label;

	BillingType(String label) {
		this.label = label;
	}

	public String getValue() {
		return this.name();
	}

    public String getLabel() {
        return label;
    }

    public static List<BillingType> categoryNeeds() {
        return Arrays.asList(EXPEND, INCOME);
    }

    public static List<BillingType> targetAccountNeeds() {
        return Arrays.asList(TRANSFER, BORROW, LOAN, RECEIVE, PAYBACK);
    }
}