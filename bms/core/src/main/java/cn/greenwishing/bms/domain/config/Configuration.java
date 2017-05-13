package cn.greenwishing.bms.domain.config;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractDomain {

    @Column(name = "`key`")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    public void update(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }

    public String description() {
        return description;
    }

    public User user() {
        return user;
    }
}
