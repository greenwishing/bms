package cn.greenwishing.bms.persistence.hibernate.activity;

import cn.greenwishing.bms.domain.activity.ActivityPaging;
import cn.greenwishing.bms.domain.activity.ActivityPlan;
import cn.greenwishing.bms.domain.activity.ActivityPlanBudget;
import cn.greenwishing.bms.domain.activity.ActivityRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.query.helper.ActivityQueryHelper;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
@Repository("activityRepository")
public class ActivityRepositoryHibernate extends AbstractRepositoryHibernate implements ActivityRepository {
    @Override
    public ActivityPaging findActivityPaging(ActivityPaging paging) {
        ActivityQueryHelper queryHelper = new ActivityQueryHelper(getHibernateTemplate(), paging);
        return queryHelper.queryResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityPlan> findActivityPlansByActivityGuid(String activityGuid) {
        return getHibernateTemplate().executeWithNativeSession(session -> {
            String queryString = "from ActivityPlan p where p.activity.guid=:activityGuid order by p.dateFrom";
            Query<ActivityPlan> query = session.createQuery(queryString);
            query.setParameter("activityGuid", activityGuid);
            return query.list();
        });
    }

    @Override
    public List<ActivityPlanBudget> findActivityPlanBudgetsByPlanGuid(String planGuid) {
        return getHibernateTemplate().executeWithNativeSession(session -> {
            String queryString = "from ActivityPlanBudget b where b.plan.guid=:planGuid order by b.id";
            Query<ActivityPlanBudget> query = session.createQuery(queryString);
            query.setParameter("planGuid", planGuid);
            return query.list();
        });
    }
}
