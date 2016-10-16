package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

public interface BillingRepository extends Repository {

    BillingPaging findBillingByPaging(BillingPaging paging);

    List<Object[]> loadNearestStatistics(BillingType billingType, Integer size);

    List<BillingCategory> findBillingCategoryByUserGuid(String userGuid);

    List<BillingSubcategory> findBillingSubcategory(String categoryGuid);

    List<BillingTemplate> findBillingTemplateByUserGuid(String userGuid);

    List<BillingCategory> findBillingCategoryByType(BillingType billingType, String userGuid);

    BillingTemplate findBillTemplate(User user, BillingType type, BillingCategory category, BillingSubcategory subcategory);

    List<BillingStatistics> loadBillingStatistics(String userGuid, BillingType billingType, LocalDate startDate, LocalDate endDate, String group);

    List<BillingAccount> findBillingAccounts(String userGuid);
}
