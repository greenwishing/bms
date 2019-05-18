package cn.greenwishing.bms.dto.activity;

import cn.greenwishing.bms.domain.activity.ActivityPlanBudget;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public class ActivityPlanBudgetDTO extends AbstractDTO {

    private String activityGuid;
    private String planGuid;
    private String guid;
    private String name;
    private String remark;
    private String amount;
    private String actualAmount;

    public ActivityPlanBudgetDTO() {
    }

    public ActivityPlanBudgetDTO(ActivityPlanBudget budget) {
        this.guid = budget.guid();
        this.name = budget.name();
        this.remark = budget.remark();
        this.amount = NumberUtils.toString(budget.amount());
        this.actualAmount = NumberUtils.toString(budget.actualAmount());
    }

    public static List<ActivityPlanBudgetDTO> toDTOs(List<ActivityPlanBudget> budgets) {
        List<ActivityPlanBudgetDTO> budgetDTOs = new ArrayList<>();
        for (ActivityPlanBudget budget : budgets) {
            ActivityPlanBudgetDTO budgetDTO = new ActivityPlanBudgetDTO(budget);
            budgetDTOs.add(budgetDTO);
        }
        return budgetDTOs;
    }

    public String getActivityGuid() {
        return activityGuid;
    }

    public void setActivityGuid(String activityGuid) {
        this.activityGuid = activityGuid;
    }

    public String getPlanGuid() {
        return planGuid;
    }

    public void setPlanGuid(String planGuid) {
        this.planGuid = planGuid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }
}
