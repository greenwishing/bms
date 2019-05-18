package cn.greenwishing.bms.dto.activity;

import cn.greenwishing.bms.domain.activity.Activity;
import cn.greenwishing.bms.domain.activity.ActivityPlan;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.utils.JodaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public class ActivityPlanDTO extends AbstractDTO {

    private String activityGuid;
    private String guid;
    private String name;
    private String remark;
    private String dateFrom;
    private String dateTo;
    private Boolean done;
    private String doneTime;
    private Integer order;

    public ActivityPlanDTO() {
    }

    public ActivityPlanDTO(ActivityPlan plan) {
        this.guid = plan.guid();
        this.name = plan.name();
        this.remark = plan.remark();
        this.dateFrom = JodaUtils.localDateToString(plan.dateFrom());
        this.dateTo = JodaUtils.localDateToString(plan.dateTo());
        this.done = plan.done();
        this.doneTime = JodaUtils.dateTimeToString(plan.doneTime());
        this.order = plan.order();
        Activity activity = plan.activity();
        User user = activity.user();
        if (user != null) {
            this.userGuid = user.guid();
        }
    }

    public static List<ActivityPlanDTO> toDTOs(List<ActivityPlan> plans) {
        List<ActivityPlanDTO> planDTOs = new ArrayList<>();
        for (ActivityPlan plan : plans) {
            ActivityPlanDTO planDTO = new ActivityPlanDTO(plan);
            planDTOs.add(planDTO);
        }
        return planDTOs;
    }

    public String getActivityGuid() {
        return activityGuid;
    }

    public void setActivityGuid(String activityGuid) {
        this.activityGuid = activityGuid;
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
