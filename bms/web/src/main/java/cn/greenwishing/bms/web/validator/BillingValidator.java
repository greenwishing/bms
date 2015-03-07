package cn.greenwishing.bms.web.validator;

import cn.greenwishing.bms.dto.billing.BillingDTO;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Wu Fan
 */
public class BillingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return BillingDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BillingDTO billingDTO = (BillingDTO) o;

        String name = billingDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "名称不能为空");
        }

        String amount = billingDTO.getAmount();
        if (ValidationUtils.isEmpty(amount)) {
            errors.rejectValue("amount", "amount", "金额不能为空");
        } else if (!ValidationUtils.isPriceBigDecimal(amount)) {
            errors.rejectValue("amount", "amount", "金额格式不正确");
        }
        String occurredTime = billingDTO.getOccurredTime();
        if (!ValidationUtils.isEmpty(occurredTime) && !ValidationUtils.isValidDate(occurredTime)) {
            errors.rejectValue("occurredTime", "occurredTime", "时间格式不正确");
        }
    }
}
