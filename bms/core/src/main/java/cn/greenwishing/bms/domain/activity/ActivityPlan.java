package cn.greenwishing.bms.domain.activity;

import cn.greenwishing.bms.domain.AbstractDomain;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;

/**
 * 活动计划
 * @author Wufan
 * @date 2019/5/4
 */
@Entity
@Table(name = "activity_plan")
public class ActivityPlan extends AbstractDomain {

    @JoinColumn(name = "activity_id")
    @ManyToOne(targetEntity = Activity.class)
    private Activity activity;

    @Column(name = "name")
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "date_from")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateTo;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "done_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime doneTime;

    @Column(name = "`order`")
    private Integer order;

    public ActivityPlan() {
    }

    public ActivityPlan(Activity activity) {
        this.activity = activity;
    }

    public void updateName(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public void updateDate(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public void updateDone(Boolean done) {
        this.done = done != null && done;
    }

    public Activity activity() {
        return activity;
    }

    public String name() {
        return name;
    }

    public String remark() {
        return remark;
    }

    public LocalDate dateFrom() {
        return dateFrom;
    }

    public LocalDate dateTo() {
        return dateTo;
    }

    public Boolean done() {
        return done != null && done;
    }

    public DateTime doneTime() {
        return doneTime;
    }

    public Integer order() {
        return order;
    }
}
