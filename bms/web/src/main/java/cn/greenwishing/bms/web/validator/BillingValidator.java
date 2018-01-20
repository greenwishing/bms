package cn.greenwishing.bms.web.validator;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingDTO;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Wufan
 * @date 2018/1/20
 */
@Component("billingValidator")
public class BillingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return BillingDTO.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        BillingDTO billingDTO = (BillingDTO) o;
        if (billingDTO == null) {
            errors.rejectValue("name", "name", "参数错误");
            return;
        }
        String categoryGuid = billingDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String subcategoryGuid = billingDTO.getSubcategoryGuid();
        if (ValidationUtils.isEmpty(subcategoryGuid)) {
            errors.rejectValue("subcategoryGuid", "subcategoryGuid", "请选择子分类");
        }
        String name = billingDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "名称不能为空");
        }
        String amount = billingDTO.getAmount();
        if (ValidationUtils.isEmpty(amount)) {
            errors.rejectValue("amount", "amount", "金额不能为空");
        } else if (!ValidationUtils.isAllNumber(amount)) {
            errors.rejectValue("amount", "amount", "金额格式不正确");
        }
        String occurredTime = billingDTO.getOccurredTime();
        if (!ValidationUtils.isEmpty(occurredTime) && !ValidationUtils.isValidDateTime(occurredTime)) {
            errors.rejectValue("occurredTime", "occurredTime", "时间格式不正确");
        }
        BillingType type = billingDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "缺少参数");
        } else {
            String srcAccountGuid = billingDTO.getSrcAccountGuid();
            if (ValidationUtils.isEmpty(srcAccountGuid)) {
                errors.rejectValue("srcAccountGuid", "srcAccountGuid", "请选择账户");
            }
            if (BillingType.targetAccountNeeds().contains(type)) {
                String targetAccountGuid = billingDTO.getTargetAccountGuid();
                if (ValidationUtils.isEmpty(targetAccountGuid)) {
                    errors.rejectValue("targetAccountGuid", "targetAccountGuid", "请选择目标账户");
                }
            }
        }
    }
}
