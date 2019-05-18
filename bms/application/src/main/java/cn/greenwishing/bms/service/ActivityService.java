package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.activity.ActivityDTO;
import cn.greenwishing.bms.dto.activity.ActivityPagingDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanBudgetDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanDTO;

import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public interface ActivityService {
    ActivityPagingDTO loadActivityPaging(ActivityPagingDTO pagingDTO);

    ActivityDTO loadActivityByGuid(String guid);

    void saveOrUpdateActivity(ActivityDTO activityDTO);

    List<ActivityPlanDTO> loadActivityPlans(String activityGuid);

    ActivityPlanDTO loadActivityPlanByGuid(String planGuid);

    void saveOrUpdateActivityPlan(ActivityPlanDTO planDTO);

    ActivityPlanBudgetDTO loadActivityPlanBudgetByGuid(String budgetGuid);

    void saveOrUpdateActivityPlanBudget(ActivityPlanBudgetDTO budgetDTO);

    List<ActivityPlanBudgetDTO> loadActivityPlanBudgets(String planGuid);
}
