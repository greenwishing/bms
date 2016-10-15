package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingCategory;
import cn.greenwishing.bms.domain.billing.BillingType;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Wufan
 * Date: 2015/3/7.
 */
public class BillingCategoryDTO {

    private Integer id;
    private String guid;
    private BillingType type;
    private String name;

    public BillingCategoryDTO() {
    }

    public BillingCategoryDTO(BillingCategory category) {
        this.id = category.id();
        this.guid = category.guid();
        this.type = category.type();
        this.name = category.name();
    }

    public static List<BillingCategoryDTO> toDTOs(List<BillingCategory> categories) {
        List<BillingCategoryDTO> categoryDTOs = new ArrayList<>();
        for (BillingCategory category : categories) {
            BillingCategoryDTO categoryDTO = new BillingCategoryDTO(category);
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
