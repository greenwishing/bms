package cn.greenwishing.bms.persistence.hibernate.billing;

import cn.greenwishing.bms.domain.billing.BillingAccount;
import cn.greenwishing.bms.domain.billing.BillingRepository;
import cn.greenwishing.bms.domain.billing.BillingSubcategory;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import cn.greenwishing.bms.utils.query.helper.BillingQueryHelper;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
    public List<Object[]> loadNearestStatistics(final BillingType billingType, final Integer size, final String userGuid) {
        return getHibernateTemplate().execute(session -> {
            String sql = "select date(b.occurred_time), sum(b.amount) from billing b join `user` u on b.user_id=u.id" +
                    " where u.guid=:userGuid and b.type=:type group by concat(year(b.occurred_time), month(b.occurred_time)) order by b.occurred_time desc";
            Query query = session.createNativeQuery(sql);
            query.setParameter("type", billingType.getValue());
            query.setParameter("userGuid", userGuid);
            return query.setMaxResults(size).list();
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SqlResultParser> findBillingCategoryByUserGuid(String userGuid) {
        List<Object[]> results = (List<Object[]>) getHibernateTemplate().find("select c.id, c.guid, c.type, c.name from BillingCategory c where c.user.guid=?", userGuid);
        return SqlResultParser.valueOf(results);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingSubcategory> findBillingSubcategory(String categoryGuid) {
        return (List<BillingSubcategory>) getHibernateTemplate().find("from BillingSubcategory s where s.category.guid=?", categoryGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SqlResultParser> findBillingSubcategoryByUserGuid(String userGuid) {
        List<Object[]> results = (List<Object[]>) getHibernateTemplate().find("select s.id, s.guid, s.name, c.guid from BillingSubcategory s join s.category c where c.user.guid=?", userGuid);
        return SqlResultParser.valueOf(results);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SqlResultParser> findBillingCategoryByType(BillingType billingType, String userGuid) {
        List<Object[]> results = (List<Object[]>) getHibernateTemplate().find("select c.id, c.guid, c.type, c.name from BillingCategory c where c.user.guid=? and c.type=?", userGuid, billingType);
        return SqlResultParser.valueOf(results);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingStatistics> loadBillingStatistics(String userGuid, BillingType billingType, LocalDate startDate, LocalDate endDate) {
        String queryString = "select new cn.greenwishing.bms.domain.statistics.BillingStatistics(b.type, b.category.name, b.subcategory.name, sum(b.amount))" +
                " from Billing b where b.user.guid=? and b.type=? and b.occurredTime>=? and b.occurredTime<? group by b.category.id, b.subcategory.id";
        DateTime timeStart = startDate.toDateTimeAtStartOfDay();
        DateTime timeEnd = endDate.toDateTimeAtStartOfDay().plusDays(1);
        return (List<BillingStatistics>) getHibernateTemplate().find(queryString, userGuid, billingType, timeStart, timeEnd.plusDays(1));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingAccount> findBillingAccounts(String userGuid) {
        return (List<BillingAccount>) getHibernateTemplate().find("from BillingAccount a where a.user.guid=?", userGuid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SqlResultParser> findSuggestTemplate(final BillingType type, final Integer userId, final Integer size) {
        return getHibernateTemplate().executeWithNativeSession(session -> {
            NativeQuery query = session.createNativeQuery("" +
                    "select b.id,b.guid,b.name,b.type,b.category_id,b.subcategory_id,b.src_account_id,b.target_account_id,b.amount,b.description" +
                    " from billing b join (" +
                    "SELECT max(id) id FROM billing b WHERE type = :type AND user_id = :userId GROUP BY category_id, subcategory_id,name ORDER BY count(*) DESC" +
                    ") t on b.id= t.id");
            query.setParameter("type", type.getValue());
            query.setParameter("userId", userId);
            List<Object[]> results = query.setMaxResults(size).list();
            return SqlResultParser.valueOf(results);
        });
    }

    @Override
    public SqlResultParser findAssertData(final Integer userId) {
        return getHibernateTemplate().executeWithNativeSession(session -> {
            Query query = session.createQuery("select " +
                    " sum(case when (a.type='CASH' or a.type='VIRTUAL' or a.type='DEPOSIT_CARD') then a.balance else 0 end)," +
                    " sum(case when (a.type='LOAN') then a.balance else 0 end)," +
                    " sum(case when (a.type='INDEBTED' or a.type='CREDIT_CARD') then a.balance else 0 end)," +
                    " sum(a.balance)" +
                    " from BillingAccount a where a.user.id=:userId");
            query.setParameter("userId", userId);
            Object[] result = (Object[]) query.uniqueResult();
            return new SqlResultParser(result);
        });
    }

    @Override
    public Map<String, Float> findBillingMapData(final Integer userId, final BillingType billingType, final Integer year) {
        return getHibernateTemplate().executeWithNativeSession(session -> {
            NativeQuery query = session.createNativeQuery("select date(b.occurred_time), sum(b.amount) from billing b" +
                    " where b.user_id=:userId and b.type=:type and year(b.occurred_time)=:year" +
                    "  group by date(b.occurred_time) order by b.occurred_time");
            query.setParameter("userId", userId);
            query.setParameter("type", billingType.getValue());
            query.setParameter("year", year);
            List<Object[]> results = query.list();
            List<SqlResultParser> parsers = SqlResultParser.valueOf(results);
            Map<String, Float> map = new HashMap<>();
            parsers.forEach(parser -> {
                String date = parser.nextLocalDateAsString();
                BigDecimal amount = parser.nextDecimal(BigDecimal.ZERO);
                map.put(date, amount.floatValue());
            });
            return map;
        });
    }
}
