package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;

import java.util.List;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingSubcategory extends AbstractDomain {

    private BillingCategory category;
    private String name;

    private static BillingRepository repository;
    private static BillingRepository getRepository() {
        if (repository == null) repository = InstanceFactory.getInstance(BillingRepository.class);
        return repository;
    }

    public BillingSubcategory() {
    }

    public BillingSubcategory(BillingCategory category) {
        this.category = category;
    }

    public static List<BillingSubcategory> findByCategoryGuid(String categoryGuid) {
        return getRepository().findBillingSubcategory(categoryGuid);
    }

    public static BillingSubcategory findByGuid(String guid) {
        return getRepository().findByGuid(BillingSubcategory.class, guid);
    }

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public BillingCategory category() {
        return category;
    }

    public String name() {
        return name;
    }
}
