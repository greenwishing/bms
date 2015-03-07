package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.user.User;
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
import java.util.List;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingCategory> findBillCategoryByUserGuid(String userGuid) {
        return getHibernateTemplate().find("from BillingCategory c where c.user.guid=?", userGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingSubcategory> findBillingSubcategory(String categoryGuid) {
        return getHibernateTemplate().find("from BillingSubcategory s where s.category.guid=?", categoryGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingTemplate> findBillingTemplateByUserGuid(String userGuid) {
        return getHibernateTemplate().find("from BillingTemplate t where t.user.guid=?", userGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingCategory> findBillCategoryByType(BillingType billingType, String userGuid) {
        return getHibernateTemplate().find("from BillingCategory c where c.type=? and c.user.guid=?", billingType, userGuid);
    }

    @Override
    public BillingTemplate findBillTemplate(User user, BillingType type, BillingCategory category, BillingSubcategory subcategory) {
        List list = getHibernateTemplate().find("from BillingTemplate t where t.user=? and t.type=? and t.category=? and t.subcategory=?", user, type, category, subcategory);
        return list.isEmpty() ? null : (BillingTemplate) list.get(0);
    }
}
