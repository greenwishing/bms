package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.BillingDTO;

import java.util.List;

/**
 * @author Wu Fan
 */
public interface BillingService {

    List<BillingDTO> loadBillings();

    void saveOrUpdateBilling(BillingDTO billingDTO);

    void deleteBillingByGuid(String guid);
}
