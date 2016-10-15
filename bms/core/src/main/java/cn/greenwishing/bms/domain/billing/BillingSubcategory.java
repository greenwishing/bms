package cn.greenwishing.bms.domain.billing;

import cn.greenwishing.bms.domain.AbstractDomain;

import javax.persistence.*;

/**
 * User: Wufan
 * Date: 2015/3/7.
 */
@Entity
@Table(name = "billing_subcategory")
public class BillingSubcategory extends AbstractDomain {

    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = BillingCategory.class)
    private BillingCategory category;

    @Column(name = "name")
    private String name;

    public BillingSubcategory() {
    }

    public BillingSubcategory(BillingCategory category) {
        this.category = category;
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
