package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.activity.*;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.OSSKey;
import cn.greenwishing.bms.dto.activity.ActivityDTO;
import cn.greenwishing.bms.dto.activity.ActivityPagingDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanBudgetDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanDTO;
import cn.greenwishing.bms.service.ActivityService;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public ActivityPagingDTO loadActivityPaging(ActivityPagingDTO pagingDTO) {
        ActivityPaging paging = activityRepository.findActivityPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public ActivityDTO loadActivityByGuid(String guid) {
        Activity activity = activityRepository.findByGuid(Activity.class, guid);
        return new ActivityDTO(activity);
    }

    @Override
    public void saveOrUpdateActivity(ActivityDTO activityDTO) {
        Activity activity;
        String guid = activityDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String userGuid = activityDTO.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            activity = new Activity(user);
        } else {
            activity = activityRepository.findByGuid(Activity.class, guid);
        }
        OSSKey cover = activityDTO.getCover();
        if (cover != null) {
            activity.updateCover(cover.getKey());
        }
        activity.updateName(activityDTO.getName(), activityDTO.getRemark());
        LocalDate dateFrom = null;
        LocalDate dateTo = null;
        String dateFromStr = activityDTO.getDateFrom();
        String dateToStr = activityDTO.getDateTo();
        if (ValidationUtils.isValidDate(dateFromStr) && ValidationUtils.isValidDate(dateToStr)) {
            dateFrom = JodaUtils.parseLocalDate(dateFromStr);
            dateTo = JodaUtils.parseLocalDate(dateToStr);
        }
        activity.updateDate(dateFrom, dateTo);
        activityRepository.saveOrUpdate(activity);
    }

    @Override
    public List<ActivityPlanDTO> loadActivityPlans(String activityGuid) {
        List<ActivityPlan> plans = activityRepository.findActivityPlansByActivityGuid(activityGuid);
        return ActivityPlanDTO.toDTOs(plans);
    }

    @Override
    public ActivityPlanDTO loadActivityPlanByGuid(String planGuid) {
        ActivityPlan plan = activityRepository.findByGuid(ActivityPlan.class, planGuid);
        return new ActivityPlanDTO(plan);
    }

    @Override
    public void saveOrUpdateActivityPlan(ActivityPlanDTO planDTO) {
        ActivityPlan plan;
        String guid = planDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String activityGuid = planDTO.getActivityGuid();
            Activity activity = activityRepository.findByGuid(Activity.class, activityGuid);
            plan = new ActivityPlan(activity);
        } else {
            plan = activityRepository.findByGuid(ActivityPlan.class, guid);
        }
        plan.updateName(planDTO.getName(), planDTO.getRemark());
        LocalDate dateFrom = null;
        LocalDate dateTo = null;
        String dateFromStr = planDTO.getDateFrom();
        String dateToStr = planDTO.getDateTo();
        if (ValidationUtils.isValidDate(dateFromStr) && ValidationUtils.isValidDate(dateToStr)) {
            dateFrom = JodaUtils.parseLocalDate(dateFromStr);
            dateTo = JodaUtils.parseLocalDate(dateToStr);
        }
        plan.updateDate(dateFrom, dateTo);
        plan.updateDone(planDTO.getDone());
        activityRepository.saveOrUpdate(plan);
    }

    @Override
    public ActivityPlanBudgetDTO loadActivityPlanBudgetByGuid(String budgetGuid) {
        ActivityPlanBudget budget = activityRepository.findByGuid(ActivityPlanBudget.class, budgetGuid);
        return new ActivityPlanBudgetDTO(budget);
    }

    @Override
    public void saveOrUpdateActivityPlanBudget(ActivityPlanBudgetDTO budgetDTO) {
        ActivityPlanBudget budget;
        String guid = budgetDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String planGuid = budgetDTO.getPlanGuid();
            ActivityPlan activityPlan = activityRepository.findByGuid(ActivityPlan.class, planGuid);
            budget = new ActivityPlanBudget(activityPlan);
        } else {
            budget = activityRepository.findByGuid(ActivityPlanBudget.class, guid);
        }
        budget.updateName(budgetDTO.getName(), budgetDTO.getRemark());
        BigDecimal amount = null;
        String amountStr = budgetDTO.getAmount();
        if (ValidationUtils.isPositiveBigDecimal(amountStr)) {
            amount = new BigDecimal(amountStr);
        }

        BigDecimal actualAmount = null;
        String actualAmountStr = budgetDTO.getActualAmount();
        if (ValidationUtils.isPositiveBigDecimal(actualAmountStr)) {
            actualAmount = new BigDecimal(actualAmountStr);
        }
        budget.updateAmount(amount);
        budget.updateActualAmount(actualAmount);
        activityRepository.saveOrUpdate(budget);
    }

    @Override
    public List<ActivityPlanBudgetDTO> loadActivityPlanBudgets(String planGuid) {
        List<ActivityPlanBudget> budgets = activityRepository.findActivityPlanBudgetsByPlanGuid(planGuid);
        return ActivityPlanBudgetDTO.toDTOs(budgets);
    }
}
