package cn.greenwishing.bms.domain.feedback;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2018/4/21
 */
@Entity
@Table(name = "`feedback`")
public class Feedback extends AbstractDomain {

    @Column(name = "`type`")
    @Enumerated(EnumType.STRING)
    private FeedbackType type;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "parent_id")
    @ManyToOne(targetEntity = Feedback.class)
    private Feedback parent;

    @JoinColumn(name = "parent_id")
    @OneToMany(targetEntity = Feedback.class)
    private List<Feedback> replies = new ArrayList<>();

    public Feedback() {
    }

    public Feedback(User user, FeedbackType type, String content) {
        this.type = type;
        this.content = content;
        this.user = user;
    }

    public void updateParent(Feedback parent) {
        this.parent = parent;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public Feedback parent() {
        return parent;
    }

    public FeedbackType type() {
        return type;
    }

    public String content() {
        return content;
    }

    public String image() {
        return image;
    }

    public User user() {
        return user;
    }

    public List<Feedback> replies() {
        return replies;
    }
}
