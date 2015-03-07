package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.AbstractPagingDTO;
import cn.greenwishing.bms.utils.StringDecoder;
import cn.greenwishing.bms.utils.paging.BillingPaging;

import java.util.List;

/**
 * @author Wu Fan
 */
public class BillingPagingDTO extends AbstractPagingDTO<BillingDTO, BillingPaging> {

    private String key;
    private BillingType type;
    private String dateFrom;
    private String dateTo;

    @Override
    public BillingPaging toPaging() {
        return new BillingPaging(currentPage, pageSize, key, type, dateFrom, dateTo);
    }

    @Override
    protected void convertList(BillingPaging paging) {
        List<Billing> list = paging.getList();
        this.list = BillingDTO.toDTOs(list);
    }

    @Override
    public List<BillingDTO> getList() {
        return list;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringDecoder.decode(key);
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
