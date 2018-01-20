package cn.greenwishing.bms.dto.statistics.tree;

import cn.greenwishing.bms.domain.billing.BillingAccountSetting;
import cn.greenwishing.bms.domain.billing.BillingType;

/**
 * @author Wufan
 * @date 2018/1/14
 */
public class BillingTypeNode extends TreeNode {

    private BillingAccountSetting src;
    private BillingAccountSetting target;

    public BillingTypeNode(BillingType billingType) {
        super(billingType);
        this.src = billingType.getSrc();
        this.target = billingType.getTarget();
    }

    public BillingAccountSetting getSrc() {
        return src;
    }

    public BillingAccountSetting getTarget() {
        return target;
    }
}
