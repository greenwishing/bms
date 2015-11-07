package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
@Entity
@Table(name = "billing_template")
public class BillingTemplate extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BillingType type;

    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = BillingCategory.class)
    private BillingCategory category;

    @JoinColumn(name = "subcategory_id")
    @ManyToOne(targetEntity = BillingSubcategory.class)
    private BillingSubcategory subcategory;

    @Column(name = "amount")
    private BigDecimal amount;

    public BillingTemplate() {
    }

    public BillingTemplate(User user) {
        this.user = user;
    }

    public void update(String name, BillingType type, BillingCategory category, BillingSubcategory subcategory, BigDecimal amount) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
        this.amount = amount;
    }

    public User user() {
        return user;
    }

    public BillingType type() {
        return type;
    }

    public BillingCategory category() {
        return category;
    }

    public BillingSubcategory subcategory() {
        return subcategory;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String name() {
        return name;
    }
}
