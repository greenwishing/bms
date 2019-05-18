package cn.greenwishing.bms.dto.activity;

import cn.greenwishing.bms.domain.activity.Activity;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.dto.OSSKey;
import cn.greenwishing.bms.utils.JodaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public class ActivityDTO extends AbstractDTO {
    private String guid;
    private OSSKey cover = new OSSKey();
    private String name;
    private String remark;
    private String dateFrom;
    private String dateTo;
    private Boolean done;
    private String doneTime;

    public ActivityDTO() {
    }

    public ActivityDTO(Activity activity) {
        this.guid = activity.guid();
        this.name = activity.name();
        this.cover = new OSSKey(activity.cover());
        this.remark = activity.remark();
        this.dateFrom = JodaUtils.localDateToString(activity.dateFrom());
        this.dateTo = JodaUtils.localDateToString(activity.dateTo());
        this.done = activity.done();
        this.doneTime = JodaUtils.dateTimeToString(activity.doneTime());
        User user = activity.user();
        if (user != null) {
            this.userGuid = user.guid();
        }
    }

    public static List<ActivityDTO> toDTOs(List<Activity> list) {
        List<ActivityDTO> activities = new ArrayList<>();
        for (Activity activity : list) {
            activities.add(new ActivityDTO(activity));
        }
        return activities;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public OSSKey getCover() {
        return cover;
    }

    public void setCover(OSSKey cover) {
        this.cover = cover;
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
}
