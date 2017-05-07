package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import cn.greenwishing.bms.utils.query.helper.BillingQueryHelper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("billingRepository")
public class BillingRepositoryHibernate extends AbstractRepositoryHibernate implements BillingRepository {

    @Override
    public BillingPaging findBillingByPaging(BillingPaging paging) {
        BillingQueryHelper helper = new BillingQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> loadNearestStatistics(final BillingType billingType, final Integer size) {
        final String userGuid = SecurityHolder.getUserGuid();
        return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
                String sql = "select date(b.occurred_time), sum(b.amount) from billing b join `user` u on b.user_id=u.id" +
                        " where u.guid=:userGuid and b.type=:type group by concat(year(b.occurred_time), month(b.occurred_time)) order by b.occurred_time desc";
                Query query = session.createSQLQuery(sql);
                query.setParameter("type", billingType.getValue());
                query.setParameter("userGuid", userGuid);
                return query.setMaxResults(size).list();
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingCategory> findBillingCategoryByUserGuid(String userGuid) {
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
    public List<BillingCategory> findBillingCategoryByType(BillingType billingType, String userGuid) {
        return getHibernateTemplate().find("from BillingCategory c where c.type=? and c.user.guid=?", billingType, userGuid);
    }

    @Override
    public BillingTemplate findBillTemplate(User user, BillingType type, BillingCategory category, BillingSubcategory subcategory) {
        List list = getHibernateTemplate().find("from BillingTemplate t where t.user=? and t.type=? and t.category=? and t.subcategory=?", user, type, category, subcategory);
        return list.isEmpty() ? null : (BillingTemplate) list.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingStatistics> loadBillingStatistics(String userGuid, BillingType billingType, LocalDate startDate, LocalDate endDate) {
        String queryString = "select new cn.greenwishing.bms.domain.statistics.BillingStatistics(b.type, b.category.name, b.subcategory.name, sum(b.amount))" +
                " from Billing b where b.user.guid=? and b.type=? and b.occurredTime>=? and b.occurredTime<? group by b.category.id, b.subcategory.id";
        return getHibernateTemplate().find(queryString, userGuid, billingType, startDate, endDate.plusDays(1));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingAccount> findBillingAccounts(String userGuid) {
        return getHibernateTemplate().find("from BillingAccount a where a.user.guid=?", userGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SqlResultParser> findSuggestTemplate(final BillingType type, final Integer userId, final Integer size) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<SqlResultParser>>() {
            @Override
            public List<SqlResultParser> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery("select b.id,b.guid,b.name,b.type,b.category_id,b.subcategory_id,b.src_account_id,b.target_account_id,b.amount from billing b join (" +
                        "SELECT max(id) id FROM billing b WHERE type = :type AND user_id = :userId GROUP BY category_id, subcategory_id,name ORDER BY count(*) DESC" +
                        ") t on b.id= t.id");
                query.setParameter("type", type.getValue());
                query.setParameter("userId", userId);
                List<Object[]> results = query.setMaxResults(size).list();
                return SqlResultParser.valueOf(results);
            }
        });
    }

    @Override
    public SqlResultParser findAssertData(final Integer userId) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<SqlResultParser>() {
            @Override
            public SqlResultParser doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select " +
                        " sum(case when (a.type='CASH' or a.type='VIRTUAL' or a.type='DEPOSIT_CARD') then a.balance else 0 end)," +
                        " sum(case when (a.type='LOAN') then a.balance else 0 end)," +
                        " sum(case when (a.type='INDEBTED' or a.type='CREDIT_CARD') then a.balance else 0 end)," +
                        " sum(a.balance)" +
                        " from BillingAccount a where a.user.id=:userId");
                query.setParameter("userId", userId);
                Object[] result = (Object[]) query.uniqueResult();
                return new SqlResultParser(result);
            }
        });
    }

    @Override
    public Map<String, Float> findBillingMapData(final Integer userId, final BillingType billingType, final Integer year) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Map<String, Float>>() {
            @Override
            @SuppressWarnings("unchecked")
            public Map<String, Float> doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("select date(b.occurred_time), sum(b.amount) from billing b" +
                        " where b.user_id=:userId and b.type=:type and year(b.occurred_time)=:year" +
                        "  group by date(b.occurred_time) order by b.occurred_time");
                query.setParameter("userId", userId);
                query.setParameter("type", billingType.getValue());
                query.setParameter("year", year);
                List<Object[]> results = query.list();
                List<SqlResultParser> parsers = SqlResultParser.valueOf(results);
                Map<String, Float> map = new HashMap<>();
                for (SqlResultParser parser : parsers) {
                    String date = parser.nextLocalDateAsString();
                    BigDecimal amount = parser.nextDecimal(BigDecimal.ZERO);
                    map.put(date, amount.floatValue());
                }
                return map;
            }
        });
    }
}
