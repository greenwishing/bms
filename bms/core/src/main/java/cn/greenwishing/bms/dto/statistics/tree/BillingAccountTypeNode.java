package cn.greenwishing.bms.dto.statistics.tree;

import cn.greenwishing.bms.domain.billing.BillingAccountType;

/**
 * @author Wufan
 * @date 2018/1/14
 */
public class BillingAccountTypeNode extends TreeNode {

    private boolean loan;

    public BillingAccountTypeNode(BillingAccountType accountType) {
        super(accountType);
        this.loan = accountType.isLoan();
    }

    public boolean isLoan() {
        return loan;
    }
}
