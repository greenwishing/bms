package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface BillingRepository extends Repository {

    BillingPaging findBillingByPaging(BillingPaging paging);

    List<Object[]> loadNearestStatistics(BillingType billingType, Integer size, String userGuid);

    List<SqlResultParser> findBillingCategoryByUserGuid(String userGuid);

    List<BillingSubcategory> findBillingSubcategory(String categoryGuid);

    List<SqlResultParser> findBillingSubcategoryByUserGuid(String userGuid);

    List<SqlResultParser> findBillingCategoryByType(BillingType billingType, String userGuid);

    List<BillingStatistics> loadBillingStatistics(String userGuid, BillingType billingType, LocalDate startDate, LocalDate endDate);

    List<BillingAccount> findBillingAccounts(String userGuid);

    List<SqlResultParser> findSuggestTemplate(BillingType type, Integer userId, Integer size);

    SqlResultParser findAssertData(Integer userId);

    Map<String, Float> findBillingMapData(Integer userId, BillingType billingType, Integer year);
}
