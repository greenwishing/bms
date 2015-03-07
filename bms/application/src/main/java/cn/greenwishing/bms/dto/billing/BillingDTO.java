package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.BillingCategory;
import cn.greenwishing.bms.domain.billing.BillingSubcategory;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Fan
 */
public class BillingDTO {

    private String guid;
    private String name;
    private String description;
    private BillingType type;
    private String categoryGuid;
    private String categoryName;
    private String subcategoryGuid;
    private String subcategoryName;
    private String amount;
    private String occurredTime = JodaUtils.today().toString(JodaUtils.DATE_FORMAT);
    private String occurredUserGuid = SecurityHolder.getUserGuid();

    private boolean createTemplate;

    public BillingDTO(){}

    public BillingDTO(Billing billing) {
        this.guid = billing.guid();
        this.name = billing.name();
        this.type = billing.type();
        BillingCategory category = billing.category();
        if (category != null) {
            this.categoryGuid = category.guid();
            this.categoryName = category.name();
        }
        BillingSubcategory subcategory = billing.subcategory();
        if (subcategory != null) {
            this.subcategoryGuid = subcategory.guid();
            this.subcategoryName = subcategory.name();
        }
        this.amount = NumberUtils.priceFormat(billing.amount());
        this.description = billing.description();
        this.occurredTime = billing.occurredTime().toString();
    }

    public static List<BillingDTO> toDTOs(List<Billing> billings) {
        List<BillingDTO> billingDTOs = new ArrayList<>();
        for (Billing billing : billings) {
            BillingDTO billingDTO = new BillingDTO(billing);
            billingDTOs.add(billingDTO);
        }
        return billingDTOs;
    }

    public Billing toBilling() {
        String guid = SecurityHolder.getUserGuid();
        User occurredUser = User.findByGuid(guid);
        BigDecimal amount = new BigDecimal(this.amount);
        LocalDate occurredTime = JodaUtils.today();
        if (ValidationUtils.isNotEmpty(this.occurredTime)) {
            occurredTime = JodaUtils.parseLocalDate(this.occurredTime);
        }
        BillingCategory category = null;
        BillingSubcategory subcategory = null;
        if (ValidationUtils.isNotEmpty(categoryGuid)) {
            category = BillingCategory.findByGuid(categoryGuid);
            if (ValidationUtils.isNotEmpty(subcategoryGuid)) {
                subcategory = BillingSubcategory.findByGuid(subcategoryGuid);
            }
        }
        return new Billing(name, type, category, subcategory, amount, description, occurredTime, occurredUser, occurredUser);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getOccurredTime() {
        return occurredTime;
    }

    public void setOccurredTime(String occurredTime) {
        this.occurredTime = occurredTime;
    }

    public String getOccurredUserGuid() {
        return occurredUserGuid;
    }

    public void setOccurredUserGuid(String occurredUserGuid) {
        this.occurredUserGuid = occurredUserGuid;
    }


    public boolean isCreateTemplate() {
        return createTemplate;
    }

    public void setCreateTemplate(boolean createTemplate) {
        this.createTemplate = createTemplate;
    }
}
