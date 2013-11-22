package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.dto.BillingPagingDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Wu Fan
 */
public class BillingServiceImpl implements BillingService {

    @Override
    public BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO) {
        BillingPaging paging = Billing.findByPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public void saveOrUpdateBilling(BillingDTO billingDTO) {
        Billing billing = billingDTO.toBilling();
        billing.saveOrUpdate();
    }

    @Override
    public void deleteBillingByGuid(String guid) {
        Billing.deleteByGuid(guid);
    }

    @Override
    public BigDecimal loadMonthInCount() {
        DateTime dateTime = JodaUtils.dayOfCurrentMonth(16);
        return Billing.loadMonthInCountByStartTime(dateTime);
    }

    @Override
    public BigDecimal loadMonthOutCount() {
        DateTime dateTime = JodaUtils.dayOfCurrentMonth(16);
        return Billing.loadMonthOutCountByStartTime(dateTime);
    }
}
