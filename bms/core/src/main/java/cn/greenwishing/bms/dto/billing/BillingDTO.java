package cn.greenwishing.bms.dto.billing;

import cn.greenwishing.bms.domain.billing.*;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import cn.greenwishing.bms.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
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
    private String srcAccountGuid;
    private String srcAccountName;
    private String targetAccountGuid;
    private String targetAccountName;
    private String amount;
    private BigDecimal _amount = BigDecimal.ZERO;
    private String occurredTime = JodaUtils.today().toString(JodaUtils.DATE_FORMAT);
    private String occurredUserGuid;
    private BillingStatus status;
    private String settleTime;

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
        BillingAccount srcAccount = billing.srcAccount();
        if (srcAccount != null) {
            this.srcAccountGuid = srcAccount.guid();
            this.srcAccountName = srcAccount.name();
        }
        BillingAccount targetAccount = billing.targetAccount();
        if (targetAccount != null) {
            this.targetAccountGuid = targetAccount.guid();
            this.targetAccountName = targetAccount.name();
        }
        this._amount = billing.amount();
        this.amount = NumberUtils.toString(billing.amount().abs());
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

    public String getSrcAccountGuid() {
        return srcAccountGuid;
    }

    public void setSrcAccountGuid(String srcAccountGuid) {
        this.srcAccountGuid = srcAccountGuid;
    }

    public String getSrcAccountName() {
        return srcAccountName;
    }

    public String getTargetAccountGuid() {
        return targetAccountGuid;
    }

    public void setTargetAccountGuid(String targetAccountGuid) {
        this.targetAccountGuid = targetAccountGuid;
    }

    public String getTargetAccountName() {
        return targetAccountName;
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

    public BillingStatus getStatus() {
        return status;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public String getClassName() {
        List<String> classes = new ArrayList<>();
        if (_amount.compareTo(BigDecimal.ZERO) > 0) {
            classes.add("POSITIVE");
        } else if (_amount.compareTo(BigDecimal.ZERO) < 0) {
            classes.add("NEGATIVE");
        }
        if (BillingStatus.RECEIVED == status || BillingStatus.PAYED == status) {
            classes.add("SETTLED");
        }
        return StringUtils.join(classes, " ");
    }
}
