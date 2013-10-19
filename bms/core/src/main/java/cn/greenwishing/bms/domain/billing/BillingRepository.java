package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.paging.BillingPaging;

public interface BillingRepository extends Repository {

    BillingPaging findBillingByPaging(BillingPaging paging);
}
