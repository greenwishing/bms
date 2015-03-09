package cn.greenwishing.bms.domain.statistics;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.NumberUtils;

import java.math.BigDecimal;

/**
 * @author Wufan
 * @date 2015/3/9.
 */
public class BillingStatistics {

    private BillingType type;
    private String category;
    private String subcategory;
    private BigDecimal amount;

    public BillingStatistics(BillingType type, String category, String subcategory, BigDecimal amount) {
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
        this.amount = amount;
    }

    public String getType() {
        return type.getLabel();
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getAmount() {
        return NumberUtils.priceFormat(amount);
    }
}
