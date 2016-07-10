package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Fan
 */
@Component
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
    private BigDecimal _amount;
    private String occurredTime = JodaUtils.today().toString(JodaUtils.DATE_FORMAT);
    private String occurredUserGuid;
    private BillingStatus status;
    private String settleTime;

    private boolean createTemplate;

    public BillingDTO(){
    }

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
        this._amount = billing.amount();
        this.amount = NumberUtils.toString(billing.amount());
        this.description = billing.description();
        this.occurredTime = JodaUtils.localDateToString(billing.occurredTime());
        this.settleTime = JodaUtils.dateTimeToString(billing.settleTime());
        this.status = billing.status();
    }

    public static List<BillingDTO> toDTOs(List<Billing> billings) {
        List<BillingDTO> billingDTOs = new ArrayList<>();
        for (Billing billing : billings) {
            BillingDTO billingDTO = new BillingDTO(billing);
            billingDTOs.add(billingDTO);
        }
        return billingDTOs;
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

    public BillingStatus getStatus() {
        return status;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public boolean isNegate() {
        return _amount.compareTo(BigDecimal.ZERO) == -1;
    }
}
