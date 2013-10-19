package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.dto.BillingPagingDTO;

import java.util.List;

/**
 * @author Wu Fan
 */
public interface BillingService {

    BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO);

    void saveOrUpdateBilling(BillingDTO billingDTO);

    void deleteBillingByGuid(String guid);
}
