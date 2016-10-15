package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingCategory;
import cn.greenwishing.bms.domain.billing.BillingSubcategory;
import cn.greenwishing.bms.domain.billing.BillingTemplate;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Wufan
 * Date: 2015/3/7.
 */
public class BillingTemplateDTO {

    private Integer id;
    private String guid;
    private String name;
    private BillingType type;
    private String categoryGuid;
    private String categoryName;
    private String subcategoryGuid;
    private String subcategoryName;
    private String amount;

    // json show
    private Float amountFloat = 0F;

    public BillingTemplateDTO() {
    }

    public BillingTemplateDTO(BillingTemplate template) {
        this.id = template.id();
        this.guid = template.guid();
        this.name = template.name();
        this.type = template.type();
        BillingCategory category = template.category();
        if (category != null) {
            this.categoryGuid = category.guid();
            this.categoryName = category.name();
        }
        BillingSubcategory subcategory = template.subcategory();
        if (subcategory != null) {
            this.subcategoryGuid = subcategory.guid();
            this.subcategoryName = subcategory.name();
        }
        BigDecimal amount = template.amount().abs();
        this.amount = NumberUtils.toString(amount);
        this.amountFloat = amount.floatValue();
    }

    public static List<BillingTemplateDTO> toDTOs(List<BillingTemplate> templates) {
        List<BillingTemplateDTO> templateDTOs = new ArrayList<>();
        for (BillingTemplate template : templates) {
            BillingTemplateDTO templateDTO = new BillingTemplateDTO(template);
            templateDTOs.add(templateDTO);
        }
        return templateDTOs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubcategoryGuid() {
        return subcategoryGuid;
    }

    public void setSubcategoryGuid(String subcategoryGuid) {
        this.subcategoryGuid = subcategoryGuid;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Float getAmountFloat() {
        return amountFloat;
    }
}
