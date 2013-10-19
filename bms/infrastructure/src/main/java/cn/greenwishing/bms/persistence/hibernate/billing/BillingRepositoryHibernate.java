package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.BillingRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.query.helper.BillingQueryHelper;

public class BillingRepositoryHibernate extends AbstractRepositoryHibernate implements BillingRepository {

    @Override
    public BillingPaging findBillingByPaging(BillingPaging paging) {
        BillingQueryHelper helper = new BillingQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }
}
