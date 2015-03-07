package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingCategory;
import cn.greenwishing.bms.domain.billing.BillingSubcategory;
import cn.greenwishing.bms.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingSubcategoryDTO {

    private Integer id;
    private String guid;
    private String name;

    private String categoryGuid;

    public BillingSubcategoryDTO() {
    }

    public BillingSubcategoryDTO(BillingSubcategory subcategory) {
        this.id = subcategory.id();
        this.guid = subcategory.guid();
        this.name = subcategory.name();
    }

    public BillingSubcategory toBillingSubcategory() {
        BillingSubcategory subcategory;
        if (ValidationUtils.isNotEmpty(guid)) {
            subcategory = BillingSubcategory.findByGuid(guid);
        } else {
            BillingCategory category = BillingCategory.findByGuid(categoryGuid);
            subcategory = new BillingSubcategory(category);
        }
        subcategory.update(name);
        return subcategory;
    }

    public static List<BillingSubcategoryDTO> toDTO(List<BillingSubcategory> subcategories) {
        List<BillingSubcategoryDTO> subcategoryDTOs = new ArrayList<>();
        for (BillingSubcategory subcategory : subcategories) {
            BillingSubcategoryDTO subcategoryDTO = new BillingSubcategoryDTO(subcategory);
            subcategoryDTOs.add(subcategoryDTO);
        }
        return subcategoryDTOs;
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

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
