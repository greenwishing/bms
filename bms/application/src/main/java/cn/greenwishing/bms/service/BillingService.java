package cn.greenwishing.bms.service;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wu Fan
 */
public interface BillingService {

    BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO);

    void saveOrUpdateBilling(BillingDTO billingDTO);

    void deleteBillingByGuid(String guid);

    BigDecimal loadMonthInCount();

    BigDecimal loadMonthOutCount();

    List<BillingCategoryDTO> loadBillingCategory();

    BillingCategoryDTO loadBillingCategoryByGuid(String guid);

    void saveOrUpdateBillingCategory(BillingCategoryDTO categoryDTO);

    BillingSubcategoryDTO loadBillingSubcategoryByGuid(String guid);

    void saveOrUpdateBillingSubcategory(BillingSubcategoryDTO subcategoryDTO);

    List<BillingSubcategoryDTO> loadBillingSubcategory(String categoryGuid);

    List<BillingTemplateDTO> loadBillingTemplate();

    BillingTemplateDTO loadBillingTemplateByGuid(String guid);

    void saveOrUpdateBillingTemplate(BillingTemplateDTO templateDTO);

    List<BillingCategoryDTO> loadBillingCategoryByType(BillingType billingType);

    void changeBillingCategory(String guid, BillingType billingType, String categoryGuid, String subcategoryGuid);
}
