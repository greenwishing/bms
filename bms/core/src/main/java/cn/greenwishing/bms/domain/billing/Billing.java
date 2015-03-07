package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.paging.BillingPaging;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

public class Billing extends AbstractDomain {

	private String name;
	private BillingType type;
    private BillingCategory category;
    private BillingSubcategory subcategory;
	private BigDecimal amount;
    private String description;
    /**
     * 发生时间
     */
	private LocalDate occurredTime;
    /**
     * 发生人
     */
	private User occurredUser;
	private User operator;

    private static BillingRepository repository;
    private static BillingRepository getRepository() {
        if (repository == null) repository = InstanceFactory.getInstance(BillingRepository.class);
        return repository;
    }

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

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
    }

    public static BillingPaging findByPaging(BillingPaging paging) {
        return getRepository().findBillingByPaging(paging);
    }

    public static void deleteByGuid(String guid) {
        getRepository().remove(Billing.class, guid);
    }

    public static BigDecimal loadMonthInCountByStartTime(DateTime dateTime) {
        return getRepository().loadMonthInCountByStartTime(dateTime);
    }

    public static BigDecimal loadMonthOutCountByStartTime(DateTime dateTime) {
        return getRepository().loadMonthOutCountByStartTime(dateTime);
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