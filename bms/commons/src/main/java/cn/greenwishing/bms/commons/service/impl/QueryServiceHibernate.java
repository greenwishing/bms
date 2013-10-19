package cn.greenwishing.bms.commons.service.impl;

import cn.greenwishing.bms.commons.service.QueryService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * User: WuFan
 * Date: 13-7-4
 */
public class QueryServiceHibernate extends HibernateDaoSupport implements QueryService {

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql, Object... params) {
        return getHibernateTemplate().find(hql, params);
    }

    @Override
    public <T> T singleResult(final String hql, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<T>() {
            @Override
            @SuppressWarnings("unchecked")
            public T doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQueryAndSetParams(session, hql, params);
                return (T) query.uniqueResult();
            }
        });
    }

    @Override
    public <T> List<T> queryResult(final String hql, final long firstRow, final int pageSize, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            @SuppressWarnings("unchecked")
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQueryAndSetParams(session, hql, params);
                query.setFirstResult(Long.valueOf(firstRow).intValue()).setMaxResults(pageSize);
                return query.list();
            }
        });
    }

    @Override
    public long count(final String hql, final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQueryAndSetParams(session, hql, params);
                List count = query.list();
                return (Long) count.get(0);
            }
        });
    }

    private Query createQueryAndSetParams(Session session, String hql, Object... params) {
        Query query = session.createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query = query.setParameter(i, params[i]);
        }
        return query;
    }
}
