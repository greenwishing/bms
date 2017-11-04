package cn.greenwishing.bms.domain.moment;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.*;

/**
 * 时间记账
 * @author Frank wu
 * @date 2017/3/4
 */
@Entity
@Table(name = "moment")
public class Moment extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "moment_type_id")
    @ManyToOne(targetEntity = MomentType.class)
    private MomentType type;

    @Column(name = "date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date;

    @Column(name = "start_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime startTime;

    @Column(name = "end_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime endTime;

    @Column(name = "description")
    private String description;

    public Moment() {
    }

    public Moment(User user) {
        this.user = user;
    }

    public void update(MomentType type, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public User user() {
        return user;
    }

    public MomentType type() {
        return type;
    }

    public LocalDate date() {
        return date;
    }

    public LocalTime startTime() {
        return startTime;
    }

    public LocalTime endTime() {
        return endTime;
    }

    public String description() {
        return description;
    }

    public DateTime creationTime() {
        return creationTime;
    }
}
