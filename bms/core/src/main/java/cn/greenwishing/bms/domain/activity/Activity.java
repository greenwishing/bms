package cn.greenwishing.bms.domain.activity;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;

/**
 * 活动
 * @author Wufan
 * @date 2019/5/4
 */
@Entity
@Table(name = "activity")
public class Activity extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "cover")
    private String cover;

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

    public Activity() {
    }

    public Activity(User user) {
        this.user = user;
    }

    public void updateCover(String cover) {
        this.cover = cover;
    }

    public void updateName(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public void updateDate(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public User user() {
        return user;
    }

    public String cover() {
        return cover;
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

    public boolean done() {
        return done != null && done;
    }

    public DateTime doneTime() {
        return doneTime;
    }
}
