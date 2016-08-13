package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Wufan
 * @date 2016/8/13
 */
@Entity
@Table(name = "user_app")
public class UserApp extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "app_id")
    @ManyToOne(targetEntity = App.class)
    private App app;

    public UserApp(User user, App app) {
        this.user = user;
        this.app = app;
    }

    public User user() {
        return user;
    }

    public App app() {
        return app;
    }
}