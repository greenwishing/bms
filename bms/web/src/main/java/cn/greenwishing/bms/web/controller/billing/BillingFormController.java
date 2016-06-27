package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Fan
 */
@Controller
@RequestMapping("/system/billing/add")
@SessionAttributes("billingDTO")
public class BillingFormController {

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(ModelMap model) {
        model.put("types", BillingType.values());
        model.put("billingDTO", new BillingDTO());
        return "billing/billing_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingDTO billingDTO, BindingResult errors) {
        Map<String, Object> model = new HashMap<>();
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
        String categoryGuid = billingDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String subcategoryGuid = billingDTO.getSubcategoryGuid();
        if (ValidationUtils.isEmpty(subcategoryGuid)) {
            errors.rejectValue("subcategoryGuid", "subcategoryGuid", "请选择子分类");
        }
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
            model.put("errors", errors.getModel());
        } else {
            billingService.saveOrUpdateBilling(billingDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
