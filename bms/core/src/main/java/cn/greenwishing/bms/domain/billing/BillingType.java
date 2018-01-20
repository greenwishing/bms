package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.dto.Selectable;

import java.util.Arrays;
import java.util.List;

public enum BillingType implements Selectable {

    EXPEND("支出", BillingAccountSetting.valueOf("支出账户"), BillingAccountSetting.DISABLED),
    INCOME("收入", BillingAccountSetting.valueOf("收入账户"), BillingAccountSetting.DISABLED),
    TRANSFER("转账", BillingAccountSetting.valueOf("转出账户"), BillingAccountSetting.valueOf("转入账户")),
    BORROW("借入", BillingAccountSetting.valueOf("借入账户"), BillingAccountSetting.valueOf("债权人").loan()),
    LOAN("借出", BillingAccountSetting.valueOf("借出账户"), BillingAccountSetting.valueOf("债务人").loan()),
    RECEIVE("收款", BillingAccountSetting.valueOf("收款账户"), BillingAccountSetting.valueOf("债权人").loan()),
    PAYBACK("还款", BillingAccountSetting.valueOf("还款账户"), BillingAccountSetting.valueOf("债权人").loan()),
    ;

	private String label;
	private BillingAccountSetting src;
	private BillingAccountSetting target;

	BillingType(String label, BillingAccountSetting src, BillingAccountSetting target) {
		this.label = label;
		this.src = src;
		this.target = target;
	}

	@Override
    public String getValue() {
		return this.name();
	}

    @Override
    public String getLabel() {
        return label;
    }

    public BillingAccountSetting getSrc() {
        return src;
    }

    public BillingAccountSetting getTarget() {
        return target;
    }

    public static List<BillingType> categoryNeeds() {
        return Arrays.asList(EXPEND, INCOME);
    }

    public static List<BillingType> targetAccountNeeds() {
        return Arrays.asList(TRANSFER, BORROW, LOAN, RECEIVE, PAYBACK);
    }
}