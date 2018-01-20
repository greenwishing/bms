package cn.greenwishing.bms.domain.billing;

/**
 * @author Wufan
 * @date 2018/1/13
 */
public class BillingAccountSetting {

    public static final BillingAccountSetting DISABLED = new BillingAccountSetting(false);

    private boolean enabled;
    private String name;
    private boolean loan;

    private BillingAccountSetting(boolean enabled) {
        this.enabled = enabled;
    }

    private BillingAccountSetting(String name) {
        this.enabled = true;
        this.name = name;
    }

    public static BillingAccountSetting valueOf(String name) {
        return new BillingAccountSetting(name);
    }

    public BillingAccountSetting loan() {
        this.loan = true;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isLoan() {
        return loan;
    }
}
