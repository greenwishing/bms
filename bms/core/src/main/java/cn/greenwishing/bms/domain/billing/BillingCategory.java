package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * User: Wufan
 * Date: 2015/3/7.
 */
@Entity
@Table(name = "billing_category")
public class BillingCategory extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "type")
    @Enumerated(value = EnumType.STRING)
    private BillingType type;

    @Column(name = "name")
    private String name;

    public BillingCategory() {
    }

    public BillingCategory(User user) {
        this.user = user;
    }

    public void update(BillingType type, String name) {
        this.type = type;
        this.name = name;
    }

    public User user() {
        return user;
    }

    public BillingType type() {
        return type;
    }

    public String name() {
        return name;
    }
}
