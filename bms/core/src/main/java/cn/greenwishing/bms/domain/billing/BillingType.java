package cn.greenwishing.bms.domain.billing;

public enum BillingType {
    EXPEND("支出", true),
    INCOME("收入", false),
	TRANSFER("转账", true),
    BALANCE("余额", false);

	private String label;
	private boolean expend;
	
	BillingType(String label, boolean expend) {
		this.label = label;
		this.expend = expend;
	}
	
	public String getValue() {
		return this.name();
	}

    public String getLabel() {
        return label;
    }

    public boolean isExpend() {
		return expend;
	}
} 