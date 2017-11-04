package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 账户
 * @author Frank wu
 * @date 2016/10/15
 */
@Entity
@Table(name = "billing_account")
public class BillingAccount extends AbstractDomain {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private BillingAccountType type;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    public BillingAccount() {
    }

    public BillingAccount(User user) {
        this.user = user;
    }

    public void update(BillingAccountType type, String name, BigDecimal balance) {
        this.type = type;
        this.name = name;
        this.balance = balance;
    }

    public void subtractAmount(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void addAmount(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public BillingAccountType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public BigDecimal balance() {
        return balance;
    }
}
