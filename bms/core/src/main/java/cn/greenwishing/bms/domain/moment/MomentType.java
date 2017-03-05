package cn.greenwishing.bms.domain.moment;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import org.hibernate.annotations.Type;
import org.joda.time.Duration;

import javax.persistence.*;

/**
 * 时间记账类型
 * User: Wufan
 * Date: 2017/3/4
 */
@Entity
@Table(name = "moment_type")
public class MomentType extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "goal_type")
    private GoalType goalType = GoalType.NONE;

    @Column(name = "goal")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDuration")
    private Duration goal;

    public MomentType() {
    }

    public MomentType(User user) {
        this.user = user;
    }

    public void update(String name, GoalType goalType, Duration goal) {
        this.name = name;
        this.goalType = goalType;
        this.goal = goal;
    }

    public User user() {
        return user;
    }

    public String name() {
        return name;
    }

    public GoalType goalType() {
        return goalType;
    }

    public Duration goal() {
        return goal;
    }
}
