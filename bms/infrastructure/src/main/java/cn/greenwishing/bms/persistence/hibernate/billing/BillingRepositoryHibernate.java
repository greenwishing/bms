package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.BillingRepository;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.query.helper.BillingQueryHelper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BillingRepositoryHibernate extends AbstractRepositoryHibernate implements BillingRepository {

    @Override
    public BillingPaging findBillingByPaging(BillingPaging paging) {
        BillingQueryHelper helper = new BillingQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }

    @Override
    public BigDecimal loadMonthInCountByStartTime(final DateTime dateTime) {
        final String queryString = "select sum(b.amount) from Billing b where b.type=:type";
        return getHibernateTemplate().execute(new HibernateCallback<BigDecimal>() {
            @Override
            public BigDecimal doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryString);
                query.setParameter("type", BillingType.INCOME);
                return (BigDecimal) query.uniqueResult();
            }
        });
    }

    @Override
    public BigDecimal loadMonthOutCountByStartTime(final DateTime dateTime) {
        final String queryString = "select sum(b.amount) from Billing b where b.type=:type";
        return getHibernateTemplate().execute(new HibernateCallback<BigDecimal>() {
            @Override
            public BigDecimal doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryString);
                query.setParameter("type", BillingType.EXPEND);
                return (BigDecimal) query.uniqueResult();
            }
        });
    }
}
