package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.moment.Moment;
import cn.greenwishing.bms.domain.moment.MomentPaging;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class MomentQueryHelper extends AbstractQueryHelper<Moment, MomentPaging> {

    public MomentQueryHelper(HibernateTemplate hibernateTemplate, MomentPaging paging) {
        super(hibernateTemplate, paging);

        final Integer userId = SecurityHolder.getUserId();
        addFilter(new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("userId", userId);
            }

            @Override
            public String getSubHql() {
                return " and m.user.id=:userId";
            }
        });

        final String key = paging.getKey();
        if (ValidationUtils.isNotEmpty(key)) {
            addFilter(new ParameterFilter() {
                @Override
                public void setParameter(Query query) {
                    query.setParameter("key", "%" + key + "%");
                }

                @Override
                public String getSubHql() {
                    return " and m.name like :key";
                }
            });
        }

        final String typeGuid = paging.getTypeGuid();
        if (ValidationUtils.isNotEmpty(typeGuid)) {
            addFilter(new ParameterFilter() {
                @Override
                public void setParameter(Query query) {
                    query.setParameter("typeGuid", typeGuid);
                }

                @Override
                public String getSubHql() {
                    return " and m.type.guid=:typeGuid";
                }
            });
        }
    }

    @Override
    public String getResultHql() {
        return "from Moment m where 1=1" + getSubHql() + " order by m.id desc";
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from Moment m where 1=1" + getSubHql();
    }
}
