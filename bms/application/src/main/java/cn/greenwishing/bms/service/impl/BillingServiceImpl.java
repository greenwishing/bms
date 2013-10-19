package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.dto.BillingPagingDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.paging.BillingPaging;

import java.util.List;

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
}
