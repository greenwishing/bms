package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingTemplate extends AbstractDomain {

    private User user;
    private String name;
    private BillingType type;
    private BillingCategory category;
    private BillingSubcategory subcategory;
    private BigDecimal amount;

    private static BillingRepository repository;
    private static BillingRepository getRepository() {
        if (repository == null) repository = InstanceFactory.getInstance(BillingRepository.class);
        return repository;
    }

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

    public void updateAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static List<BillingTemplate> findByUserGuid(String userGuid) {
        return getRepository().findBillingTemplateByUserGuid(userGuid);
    }

    public static BillingTemplate findByBilling(User user, BillingType type, BillingCategory category, BillingSubcategory subcategory) {
        return getRepository().findBillTemplate(user, type, category, subcategory);
    }

    public static BillingTemplate findByGuid(String guid) {
        return getRepository().findByGuid(BillingTemplate.class, guid);
    }

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
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
