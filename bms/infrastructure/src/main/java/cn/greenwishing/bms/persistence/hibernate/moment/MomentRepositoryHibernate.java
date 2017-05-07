package cn.greenwishing.bms.persistence.hibernate.moment;

import cn.greenwishing.bms.domain.moment.MomentPaging;
import cn.greenwishing.bms.domain.moment.MomentRepository;
import cn.greenwishing.bms.domain.moment.MomentType;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.query.helper.MomentQueryHelper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
@Repository("momentRepository")
public class MomentRepositoryHibernate extends AbstractRepositoryHibernate implements MomentRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<MomentType> findMomentTypes(final Integer userId) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<MomentType>>() {
            @Override
            public List<MomentType> doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(MomentType.class);
                criteria.add(Restrictions.eq("user.id", userId));
                return criteria.list();
            }
        });
    }

    @Override
    public MomentPaging findMomentPaging(MomentPaging paging) {
        MomentQueryHelper helper = new MomentQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }
}
