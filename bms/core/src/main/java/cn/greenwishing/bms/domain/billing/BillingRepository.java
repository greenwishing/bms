package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public interface BillingRepository extends Repository {

    BillingPaging findBillingByPaging(BillingPaging paging);

    BigDecimal loadMonthInCountByStartTime(DateTime dateTime);

    BigDecimal loadMonthOutCountByStartTime(DateTime dateTime);
}
