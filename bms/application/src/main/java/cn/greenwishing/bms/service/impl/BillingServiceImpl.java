package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.dto.statistics.highcharts.SeriesObject;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Wu Fan
 */
@Service("billService")
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public BillingPagingDTO loadBillingPaging(BillingPagingDTO pagingDTO) {
        BillingPaging paging = billingRepository.findBillingByPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public void saveOrUpdateBilling(BillingDTO billingDTO) {
        String amountStr = billingDTO.getAmount();
        BigDecimal amount = NumberUtils.parseDecimal(amountStr);
        String categoryGuid = billingDTO.getCategoryGuid();
        String subcategoryGuid = billingDTO.getSubcategoryGuid();
        BillingCategory category = billingRepository.findByGuid(BillingCategory.class, categoryGuid);
        BillingSubcategory subcategory = billingRepository.findByGuid(BillingSubcategory.class, subcategoryGuid);
        BillingStatus status;
        BillingType billingType = category.type();
        switch (billingType) {
            case ACCOUNT_RECEIVABLE:
                status = BillingStatus.RECEIVABLE;
                break;
            case ACCOUNT_PAYABLE:
                status = BillingStatus.PAYABLE;
                break;
            default:
                status = BillingStatus.NORMAL;
        }
        String guid = SecurityHolder.getUserGuid();
        User occurredUser = userRepository.findByGuid(User.class, guid);

        LocalDate occurredTime = JodaUtils.today();
        String occurredTimeStr = billingDTO.getOccurredTime();
        if (ValidationUtils.isNotEmpty(occurredTimeStr)) {
            occurredTime = JodaUtils.parseLocalDate(occurredTimeStr);
        }

        String name = billingDTO.getName();
        BillingType type = billingDTO.getType();
        Billing billing = new Billing(name, type, category, subcategory, amount, billingDTO.getDescription(), occurredTime, occurredUser, occurredUser);
        billing.updateStatus(status);
        billingRepository.saveOrUpdate(billing);

        if (billingDTO.isCreateTemplate()) {
            BillingTemplate template = billingRepository.findBillTemplate(occurredUser, type, category, subcategory);
            if (template == null) {
                template = new BillingTemplate(occurredUser);
            }
            template.update(name, type, category, subcategory, amount);
            billingRepository.saveOrUpdate(template);
        }
    }

    @Override
    public void deleteBillingByGuid(String guid) {
        billingRepository.remove(Billing.class, guid);
    }

    @Override
    public List<SeriesObject> loadNearestStatistics(Integer size) {
        List<SeriesObject> series = new ArrayList<>();

        SeriesObject income = loadSeriesObject(BillingType.INCOME, size);
        SeriesObject expend = loadSeriesObject(BillingType.EXPEND, size);
        series.add(income);
        series.add(expend);

        return series;
    }

    private SeriesObject loadSeriesObject(BillingType billingType, Integer size) {
        List<Object[]> results = billingRepository.loadNearestStatistics(billingType, size);
        return SeriesObject.valueOf(billingType, results);
    }

    @Override
    public List<BillingCategoryDTO> loadBillingCategory() {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategory> categories = billingRepository.findBillCategoryByUserGuid(userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public BillingCategoryDTO loadBillingCategoryByGuid(String guid) {
        BillingCategory category = billingRepository.findByGuid(BillingCategory.class, guid);
        return new BillingCategoryDTO(category);
    }

    @Override
    public void saveOrUpdateBillingCategory(BillingCategoryDTO categoryDTO) {
        BillingCategory category;
        String guid = categoryDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            category = billingRepository.findByGuid(BillingCategory.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            category = new BillingCategory(user);
        }
        category.update(categoryDTO.getType(), categoryDTO.getName());
        billingRepository.saveOrUpdate(category);
    }

    @Override
    public BillingSubcategoryDTO loadBillingSubcategoryByGuid(String guid) {
        BillingSubcategory subcategory = billingRepository.findByGuid(BillingSubcategory.class, guid);
        return new BillingSubcategoryDTO(subcategory);
    }

    @Override
    public void saveOrUpdateBillingSubcategory(BillingSubcategoryDTO subcategoryDTO) {
        BillingSubcategory subcategory;
        String guid = subcategoryDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            subcategory = billingRepository.findByGuid(BillingSubcategory.class, guid);
        } else {
            String categoryGuid = subcategoryDTO.getCategoryGuid();
            BillingCategory category = billingRepository.findByGuid(BillingCategory.class, categoryGuid);
            subcategory = new BillingSubcategory(category);
        }
        subcategory.update(subcategoryDTO.getName());
        billingRepository.saveOrUpdate(subcategory);
    }

    @Override
    public List<BillingSubcategoryDTO> loadBillingSubcategory(String categoryGuid) {
        List<BillingSubcategory> subcategories = billingRepository.findBillingSubcategory(categoryGuid);
        return BillingSubcategoryDTO.toDTO(subcategories);
    }

    @Override
    public List<BillingTemplateDTO> loadBillingTemplate() {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingTemplate> templates = billingRepository.findBillingTemplateByUserGuid(userGuid);
        return BillingTemplateDTO.toDTOs(templates);
    }

    @Override
    public BillingTemplateDTO loadBillingTemplateByGuid(String guid) {
        BillingTemplate template = billingRepository.findByGuid(BillingTemplate.class, guid);
        return new BillingTemplateDTO(template);
    }

    @Override
    public void saveOrUpdateBillingTemplate(BillingTemplateDTO templateDTO) {
        BillingTemplate template;
        String guid = templateDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            template = billingRepository.findByGuid(BillingTemplate.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            template = new BillingTemplate(user);
        }
        BillingCategory category = null;
        BillingSubcategory subcategory = null;
        String categoryGuid = templateDTO.getCategoryGuid();
        String subcategoryGuid = templateDTO.getSubcategoryGuid();
        if (ValidationUtils.isNotEmpty(categoryGuid)) {
            category = billingRepository.findByGuid(BillingCategory.class, categoryGuid);
            if (ValidationUtils.isNotEmpty(subcategoryGuid)) {
                subcategory = billingRepository.findByGuid(BillingSubcategory.class, subcategoryGuid);
            }
        }
        String amountStr = templateDTO.getAmount();
        BigDecimal amount = BigDecimal.ZERO;
        if (ValidationUtils.isPositiveBigDecimal(amountStr)) {
            amount = new BigDecimal(amountStr);
        }
        template.update(templateDTO.getName(), templateDTO.getType(), category, subcategory, amount);
        billingRepository.saveOrUpdate(template);
    }

    @Override
    public List<BillingCategoryDTO> loadBillingCategoryByType(BillingType billingType) {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategory> categories = billingRepository.findBillCategoryByType(billingType, userGuid);
        return BillingCategoryDTO.toDTOs(categories);
    }

    @Override
    public List<BillingStatistics> loadBillingStatistics(String type, String group, String fromDateStr, String toDateStr) {
        BillingType billingType = EnumUtils.nameOf(BillingType.class, type);
        if (billingType == null) {
            billingType = BillingType.EXPEND;
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (ValidationUtils.isValidDate(fromDateStr) && ValidationUtils.isValidDate(toDateStr)) {
            startDate = JodaUtils.parseLocalDate(fromDateStr);
            endDate = JodaUtils.parseLocalDate(toDateStr);
        }
        if (startDate == null || endDate == null) {
            return Collections.emptyList();
        }
        String userGuid = SecurityHolder.getUserGuid();
        return billingRepository.loadBillingStatistics(userGuid, billingType, startDate, endDate, group);
    }

    @Override
    public void changeStatus(String guid, BillingStatus status) {
        Billing billing = billingRepository.findByGuid(Billing.class, guid);
        if (billing != null) {
            billing.updateStatus(status);
            billingRepository.saveOrUpdate(billing);
        }
    }
}
