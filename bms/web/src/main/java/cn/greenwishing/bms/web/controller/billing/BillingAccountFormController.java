package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingAccountDTO;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
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

/**
 * User: Wufan
 * Date: 2015/3/7.
 */
@Controller
@RequestMapping({"/system/billing/add_account", "/system/billing/edit_account"})
@SessionAttributes("billingAccountDTO")
public class BillingAccountFormController {

    public static final String FORM_VIEW = "billing/billing_account_form";

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        model.put("types", BillingAccountType.values());
        BillingAccountDTO billingAccountDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingAccountDTO = new BillingAccountDTO();
        } else {
            billingAccountDTO = billingService.loadBillingAccountByGuid(guid);
        }
        model.put("billingAccountDTO", billingAccountDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingAccountDTO accountDTO, BindingResult errors) {
        BillingAccountType type = accountDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String name = accountDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        String balance = accountDTO.getBalance();
        if (ValidationUtils.isNotEmpty(balance) && !ValidationUtils.isAllNumber(balance)) {
            errors.rejectValue("balance", "balance", "余额必须是数字");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingAccount(accountDTO);
            model.put("success", true);
            model.put("redirectUrl", "accounts");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
