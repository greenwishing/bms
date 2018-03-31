package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.utils.paging.AbstractPaging;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQueryHelper<T, P extends AbstractPaging<T>> implements QueryHelper<T> {
    final static Logger log = LogManager.getLogger(AbstractQueryHelper.class);
    private List<Filter> filters = new ArrayList<>();

    private HibernateTemplate hibernateTemplate;

    protected P paging;

    protected AbstractQueryHelper(HibernateTemplate hibernateTemplate, P paging) {
        this.hibernateTemplate = hibernateTemplate;
        this.paging = paging;
    }

    public void addFilter(Filter filter) {
        if (filter != null) {
            filters.add(filter);
        }
    }

    public List<Filter> getFilters() {
        return filters;
    }


    public String getSubHql() {
        StringBuilder subHql = new StringBuilder();
        getFilters().forEach(filter -> subHql.append(filter.getSubHql()));
        return subHql.toString();
    }

    @Override
    public abstract String getResultHql();

    public int getStartIndex() {
        return 0;
    }

    public int getPageSize() {
        return 0;
    }

    public String getTotalCountHql() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getSortHql() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Integer getTotalCount() {
        final String totalCountHql = getTotalCountHql();
        log.debug("TotalCount hql:" + totalCountHql);

        return this.hibernateTemplate.execute(session -> {
            Query query = createQuery(session, totalCountHql);
            return ((Long) query.uniqueResult()).intValue();
        });
    }

    @SuppressWarnings("unchecked")
    private List<T> getResults() {
        final String resultHql = getResultHql();
        log.debug("Result sql: " + resultHql);

        return this.hibernateTemplate.execute(session -> {
            Query query = createQuery(session, resultHql);
            int amountPerPage = getPageSize();
            if (amountPerPage == 0) {
                return query.list();
            }
            int startPosition = getStartIndex();
            return query.setFirstResult(startPosition).setMaxResults(amountPerPage).list();
        });

    }

    private Query createQuery(Session session, String resultHql) {
        Query query = session.createQuery(resultHql);
        getFilters().stream().filter(filter -> filter instanceof ParameterFilter)
        .forEach(filter -> ((ParameterFilter) filter).setParameter(query));
        return query;
    }

    public P queryResult() {
        paging.setList(getResults());
        paging.setTotalCount(getTotalCount());
        return paging;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}