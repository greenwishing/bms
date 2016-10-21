package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingAccountDTO;
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

import java.util.*;

/**
 * User: Wu Fan
 */
@Controller
@RequestMapping("/system/billing/add")
@SessionAttributes("billingDTO")
public class BillingFormController {

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(ModelMap model) {
        List<BillingAccountDTO> accounts = billingService.loadBillingAccounts();
        Map<BillingAccountType, List<BillingAccountDTO>> accountMap = new TreeMap<>();
        Map<BillingAccountType, List<BillingAccountDTO>> loanAccountMap = new TreeMap<>();
        for (BillingAccountDTO account : accounts) {
            BillingAccountType type = account.getType();
            List<BillingAccountDTO> accountList = accountMap.get(type);
            if (accountList == null) {
                accountList = new ArrayList<>();
                if (type.isLoan()) {
                    loanAccountMap.put(type, accountList);
                } else {
                    accountMap.put(type, accountList);
                }
            }
            accountList.add(account);
        }
        model.put("accountMap", accountMap);
        model.put("loanAccountMap", loanAccountMap);
        model.put("billingDTO", new BillingDTO());
        return "billing/billing_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingDTO billingDTO, BindingResult errors) {
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
        if (!ValidationUtils.isEmpty(occurredTime) && !ValidationUtils.isValidDate(occurredTime)) {
            errors.rejectValue("occurredTime", "occurredTime", "时间格式不正确");
        }
        BillingType type = billingDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "缺少参数");
        } else {
            if (BillingType.categoryNeeds().contains(type)) {
                String categoryGuid = billingDTO.getCategoryGuid();
                if (ValidationUtils.isEmpty(categoryGuid)) {
                    errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
                }
                String subcategoryGuid = billingDTO.getSubcategoryGuid();
                if (ValidationUtils.isEmpty(subcategoryGuid)) {
                    errors.rejectValue("subcategoryGuid", "subcategoryGuid", "请选择子分类");
                }
            }
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

        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBilling(billingDTO);
            model.put("success", true);
            model.put("redirectUrl", "list?type=" + billingDTO.getType());
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
