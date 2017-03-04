package cn.greenwishing.bms.domain.todo;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.Status;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.JodaUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * 待办事项
 * User: Wufan
 * Date: 2017/3/4
 */
@Entity
@Table(name = "todo")
public class Todo extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "done")
    private Boolean done = false;

    @Column(name = "done_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime doneTime;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.NORMAL;

    public Todo() {
    }

    public Todo(User user) {
        this.user = user;
    }

    public void update(String content) {
        this.content = content;
    }

    public void toggleDone() {
        if (done) {
            this.done = false;
            this.doneTime = null;
        } else {
            this.done = true;
            this.doneTime = JodaUtils.now();
        }
    }

    public void remove() {
        this.status = Status.REMOVED;
    }

    public User user() {
        return user;
    }

    public String content() {
        return content;
    }

    public DateTime creationTime() {
        return creationTime;
    }

    public boolean done() {
        return done != null && done;
    }

    public DateTime doneTime() {
        return doneTime;
    }

    public Status status() {
        return status;
    }
}
