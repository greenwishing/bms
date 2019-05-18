package cn.greenwishing.bms.domain.activity;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public interface ActivityRepository extends Repository {

    ActivityPaging findActivityPaging(ActivityPaging paging);

    List<ActivityPlan> findActivityPlansByActivityGuid(String activityGuid);

    List<ActivityPlanBudget> findActivityPlanBudgetsByPlanGuid(String planGuid);
}
