package cn.greenwishing.bms.domain.billing;

public enum BillingType {
    EXPEND("支出"),
    INCOME("收入"),
    ACCOUNT_RECEIVABLE("应收AR"),
    ACCOUNT_PAYABLE("应付AP"),
    BALANCE("余额");

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
} 