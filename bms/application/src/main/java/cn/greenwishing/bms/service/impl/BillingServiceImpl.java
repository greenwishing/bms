package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Collections;
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

        if (billingDTO.isCreateTemplate()) {
            User user = billing.occurredUser();
            String name = billing.name();
            BillingType type = billing.type();
            BillingCategory category = billing.category();
            BillingSubcategory subcategory = billing.subcategory();
            BigDecimal amount = billing.amount();
            BillingTemplate template = BillingTemplate.findByBilling(user, type, category, subcategory);
            if (template == null) {
                template = new BillingTemplate(user);
            }
            template.update(name, type, category, subcategory, amount);
            template.saveOrUpdate();
        }
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

    @Override
    public List<BillingCategoryDTO> loadBillingCategory() {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategory> categories = BillingCategory.findByUserGuid(userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public BillingCategoryDTO loadBillingCategoryByGuid(String guid) {
        BillingCategory category = BillingCategory.findByGuid(guid);
        return new BillingCategoryDTO(category);
    }

    @Override
    public void saveOrUpdateBillingCategory(BillingCategoryDTO categoryDTO) {
        BillingCategory category = categoryDTO.toBillingCategory();
        category.saveOrUpdate();
    }

    @Override
    public BillingSubcategoryDTO loadBillingSubcategoryByGuid(String guid) {
        BillingSubcategory subcategory = BillingSubcategory.findByGuid(guid);
        return new BillingSubcategoryDTO(subcategory);
    }

    @Override
    public void saveOrUpdateBillingSubcategory(BillingSubcategoryDTO subcategoryDTO) {
        BillingSubcategory subcategory = subcategoryDTO.toBillingSubcategory();
        subcategory.saveOrUpdate();
    }

    @Override
    public List<BillingSubcategoryDTO> loadBillingSubcategory(String categoryGuid) {
        List<BillingSubcategory> subcategories = BillingSubcategory.findByCategoryGuid(categoryGuid);
        return BillingSubcategoryDTO.toDTO(subcategories);
    }

    @Override
    public List<BillingTemplateDTO> loadBillingTemplate() {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingTemplate> templates = BillingTemplate.findByUserGuid(userGuid);
        return BillingTemplateDTO.toDTOs(templates);
    }

    @Override
    public BillingTemplateDTO loadBillingTemplateByGuid(String guid) {
        BillingTemplate template = BillingTemplate.findByGuid(guid);
        return new BillingTemplateDTO(template);
    }

    @Override
    public void saveOrUpdateBillingTemplate(BillingTemplateDTO templateDTO) {
        BillingTemplate template = templateDTO.toBillingTemplate();
        template.saveOrUpdate();
    }

    @Override
    public List<BillingCategoryDTO> loadBillingCategoryByType(BillingType billingType) {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategory> categories = BillingCategory.findByType(billingType, userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public List<BillingStatistics> loadBillingStatistics(String type, String group) {
        LocalDate startDate;
        LocalDate endDate;
        if ("day".equals(type)) {
            startDate = JodaUtils.today();
            endDate = JodaUtils.today();
        } else if ("week".equals(type)) {
            startDate = JodaUtils.today().minusWeeks(1);
            endDate = JodaUtils.today();
        } else if ("month".equals(type)) {
            startDate = JodaUtils.today().minusMonths(1);
            endDate = JodaUtils.today();
        } else {
            return Collections.emptyList();
        }
        String userGuid = SecurityHolder.getUserGuid();
        return Billing.loadStatistics(userGuid, startDate, endDate, group);
    }
}
