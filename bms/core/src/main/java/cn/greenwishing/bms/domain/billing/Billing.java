package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.JodaUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
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

    @JoinColumn(name = "src_account_id")
    @ManyToOne(targetEntity = BillingAccount.class)
    private BillingAccount srcAccount;

    @JoinColumn(name = "target_account_id")
    @ManyToOne(targetEntity = BillingAccount.class)
    private BillingAccount targetAccount;

    @Column(name = "amount")
	private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "occurred_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime occurredTime;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
	private User user;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
	private BillingStatus status = BillingStatus.NORMAL;

    @Column(name = "settle_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime settleTime;

    public Billing() {
    }

    public Billing(String name, BillingType type, BillingCategory category, BillingSubcategory subcategory,
                   BillingAccount srcAccount, BillingAccount targetAccount, BigDecimal amount, String description,
                   DateTime occurredTime, User user) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
        this.srcAccount = srcAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.description = description;
        this.occurredTime = occurredTime;
        this.user = user;
    }

    public void updateStatus(BillingStatus status) {
        this.status = status;
        if (BillingStatus.PAYED == status || BillingStatus.RECEIVED == status) {
            this.settleTime = JodaUtils.now();
        }
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

    public BillingAccount srcAccount() {
        return srcAccount;
    }

    public BillingAccount targetAccount() {
        return targetAccount;
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

    public DateTime occurredTime() {
        return occurredTime;
    }

    public User user() {
        return user;
    }

    public BillingStatus status() {
        return status;
    }

    public DateTime settleTime() {
        return settleTime;
    }
}