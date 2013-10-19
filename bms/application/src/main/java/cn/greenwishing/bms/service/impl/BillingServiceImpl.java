package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.service.BillingService;

import java.util.List;

/**
 * @author Wu Fan
 */
public class BillingServiceImpl implements BillingService {

    @Override
    public List<BillingDTO> loadBillings() {
        List<Billing> billings = Billing.loadAll();
        return BillingDTO.toDTOs(billings);
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
