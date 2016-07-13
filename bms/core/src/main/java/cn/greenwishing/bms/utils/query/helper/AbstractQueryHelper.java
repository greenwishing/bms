package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.utils.paging.AbstractPaging;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQueryHelper<T, P extends AbstractPaging<T>> implements QueryHelper<T> {
    final static Logger log = LogManager.getLogger(AbstractQueryHelper.class);
    private List<Filter> filters = new ArrayList<Filter>();

    private HibernateTemplate hibernateTemplate;

    protected P paging;

    public AbstractQueryHelper() {
    }

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
        List<Filter> filters = getFilters();
        for (Filter filter : filters) {
            subHql.append(filter.getSubHql());
        }
        return subHql.toString();
    }

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

        return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQuery(session, totalCountHql);
                return ((Long) query.uniqueResult()).intValue();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private List<T> getResults() {
        final String resultHql = getResultHql();
        log.debug("Result sql: " + resultHql);

        return this.hibernateTemplate.execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQuery(session, resultHql);
                int amountPerPage = getPageSize();
                if (amountPerPage == 0) {
                    return query.list();
                }
                int startPosition = getStartIndex();
                return query.setFirstResult(startPosition).setMaxResults(amountPerPage).list();
            }
        });

    }

    private Query createQuery(Session session, String resultHql) {
        Query query = session.createQuery(resultHql);
        List<Filter> filters = getFilters();
        for (Filter filter : filters) {
            if (filter instanceof ParameterFilter) {
                ParameterFilter parameterFilter = (ParameterFilter) filter;
                parameterFilter.setParameter(query);
            }
        }
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