package cn.greenwishing.bms.domain.activity;

import cn.greenwishing.bms.domain.AbstractDomain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 活动计划预算
 * @author Wufan
 * @date 2019/5/4
 */
@Entity
@Table(name = "activity_plan_budget")
public class ActivityPlanBudget extends AbstractDomain {

    @JoinColumn(name = "plan_id")
    @ManyToOne(targetEntity = ActivityPlan.class)
    private ActivityPlan plan;

    @Column(name = "name")
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    public ActivityPlanBudget() {
    }

    public ActivityPlanBudget(ActivityPlan plan) {
        this.plan = plan;
    }

    public void updateName(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public void updateAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void updateActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public ActivityPlan plan() {
        return plan;
    }

    public String name() {
        return name;
    }

    public String remark() {
        return remark;
    }

    public BigDecimal amount() {
        return amount;
    }

    public BigDecimal actualAmount() {
        return actualAmount;
    }
}
