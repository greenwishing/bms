package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.query.helper.BillingQueryHelper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
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
    @SuppressWarnings("unchecked")
    public List<Object[]> loadNearestStatistics(final BillingType billingType, final Integer size) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
                String sql = "select date(b.occurred_time), sum(b.amount) from billing b where b.type=:type group by concat(year(b.occurred_time), month(b.occurred_time)) order by b.occurred_time desc, id desc";
                Query query = session.createSQLQuery(sql);
                query.setParameter("type", billingType.getValue());
                return query.setMaxResults(size).list();
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

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingStatistics> loadBillingStatistics(String userGuid, LocalDate startDate, LocalDate endDate, String group) {
        String queryString = "select new cn.greenwishing.bms.domain.statistics.BillingStatistics(b.type, b.category.name, b.subcategory.name, sum(b.amount))" +
                " from Billing b where b.occurredUser.guid=? and b.occurredTime>=? and b.occurredTime<=?";
        if ("subcategory".equals(group)) {
            queryString += " group by b.subcategory.id";
        } else if ("category".equals(group)) {
            queryString += " group by b.category.id";
        } else if ("type".equals(group)) {
            queryString += " group by b.type";
        }
        return getHibernateTemplate().find(queryString, userGuid, startDate, endDate);
    }
}
