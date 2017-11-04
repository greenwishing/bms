package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.StringDecoder;

/**
 * @author Frank wu
 */
public class BillingPaging extends AbstractPaging<Billing> {

    private String key;
    private BillingType type;
    private String categoryGuid;
    private String subcategoryGuid;
    private String dateFrom;
    private String dateTo;

    public BillingPaging(int currentPage, int pageSize, String key, BillingType type, String categoryGuid, String subcategoryGuid, String dateFrom, String dateTo) {
        super(currentPage, pageSize);
        this.key = key;
        this.type = type;
        this.categoryGuid = categoryGuid;
        this.subcategoryGuid = subcategoryGuid;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getKey() {
        return key;
    }

    public BillingType getType() {
        return type;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public String getSubcategoryGuid() {
        return subcategoryGuid;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
