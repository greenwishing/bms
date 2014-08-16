package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.StringDecoder;

/**
 * @author Wu Fan
 */
public class BillingPaging extends AbstractPaging<Billing> {

    private String key;
    private BillingType type;
    private String dateFrom;
    private String dateTo;

    public BillingPaging(int currentPage, int pageSize, String key, BillingType type, String dateFrom, String dateTo) {
        super(currentPage, pageSize);
        this.key = key;
        this.type = type;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getKey() {
        return key;
    }

    public BillingType getType() {
        return type;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
