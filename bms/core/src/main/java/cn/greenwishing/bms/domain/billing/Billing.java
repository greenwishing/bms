package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "billing")
public class Billing extends AbstractDomain {

    @Column(name = "name")
	private String name;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
	private BillingType type;

    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = BillingCategory.class)
    private BillingCategory category;

    @JoinColumn(name = "subcategory_id")
    @ManyToOne(targetEntity = BillingSubcategory.class)
    private BillingSubcategory subcategory;

    @Column(name = "amount")
	private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "occurred_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate occurredTime;

    @JoinColumn(name = "occurred_user_id")
    @ManyToOne(targetEntity = User.class)
	private User occurredUser;

    @JoinColumn(name = "operator_id")
    @ManyToOne(targetEntity = User.class)
	private User operator;

    public Billing() {
    }

    public Billing(String name, BillingType type, BillingCategory category, BillingSubcategory subcategory, BigDecimal amount, String description, LocalDate occurredTime, User occurredUser, User operator) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
        this.amount = amount;
        this.description = description;
        this.occurredTime = occurredTime;
        this.occurredUser = occurredUser;
        this.operator = operator;
    }

    public void update(BillingType type, BillingCategory category, BillingSubcategory subcategory) {
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
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

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public BigDecimal amount() {
        return amount;
    }

    public LocalDate occurredTime() {
        return occurredTime;
    }

    public User occurredUser() {
        return occurredUser;
    }

    public User operator() {
        return operator;
    }
}