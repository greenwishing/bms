package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.activity.Activity;
import cn.greenwishing.bms.domain.activity.ActivityPaging;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public class ActivityQueryHelper extends AbstractQueryHelper<Activity, ActivityPaging> {

    public ActivityQueryHelper(HibernateTemplate hibernateTemplate, ActivityPaging paging) {
        super(hibernateTemplate, paging);

        String userGuid = paging.getUserGuid();
        if (ValidationUtils.isNotEmpty(userGuid)) {
            addFilter(userFilter(userGuid));
        }
    }

    private Filter userFilter(final String userGuid) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("userGuid", userGuid);
            }

            @Override
            public String getSubHql() {
                return " and a.user.guid=:userGuid";
            }
        };
    }

    @Override
    public String getResultHql() {
        return "from Activity a where 1=1" + getSubHql() + " order by a.id desc";
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from Activity a where 1=1 " + getSubHql();
    }
}
