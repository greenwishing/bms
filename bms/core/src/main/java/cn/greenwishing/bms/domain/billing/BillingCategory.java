package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import java.util.List;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingCategory extends AbstractDomain {

    private User user;
    private BillingType type;
    private String name;

    private static BillingRepository repository;
    private static BillingRepository getRepository() {
        if (repository == null) repository = InstanceFactory.getInstance(BillingRepository.class);
        return repository;
    }

    public BillingCategory() {
    }

    public BillingCategory(User user) {
        this.user = user;
    }

    public static List<BillingCategory> findByUserGuid(String userGuid) {
        return getRepository().findBillCategoryByUserGuid(userGuid);
    }

    public static BillingCategory findByGuid(String guid) {
        return getRepository().findByGuid(BillingCategory.class, guid);
    }

    public static List<BillingCategory> findByType(BillingType billingType, String userGuid) {
        return getRepository().findBillCategoryByType(billingType, userGuid);
    }

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
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
