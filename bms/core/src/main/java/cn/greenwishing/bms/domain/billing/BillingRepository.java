package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

public interface BillingRepository extends Repository {

    BillingPaging findBillingByPaging(BillingPaging paging);

    BigDecimal loadMonthInCountByStartTime(DateTime dateTime);

    BigDecimal loadMonthOutCountByStartTime(DateTime dateTime);

    List<BillingCategory> findBillCategoryByUserGuid(String userGuid);

    List<BillingSubcategory> findBillingSubcategory(String categoryGuid);

    List<BillingTemplate> findBillingTemplateByUserGuid(String userGuid);

    List<BillingCategory> findBillCategoryByType(BillingType billingType, String userGuid);

    BillingTemplate findBillTemplate(User user, BillingType type, BillingCategory category, BillingSubcategory subcategory);

    List<BillingStatistics> loadBillingStatistics(String userGuid, LocalDate startDate, LocalDate endDate, String group);
}
