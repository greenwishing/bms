package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingTemplateDTO;
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

import java.util.List;

/**
 * @author Wu Fan
 */
@Controller
@RequestMapping({"/system/billing/add_template", "/system/billing/edit_template"})
@SessionAttributes("billingTemplateDTO")
public class BillingTemplateFormController {

    public static final String FORM_VIEW = "billing/billing_template_form";

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(String guid, ModelMap model) {
        model.put("types", BillingType.values());
        List<BillingCategoryDTO> categories = billingService.loadBillingCategory();
        model.put("categories", categories);
        BillingTemplateDTO billingTemplateDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingTemplateDTO = new BillingTemplateDTO();
        } else {
            billingTemplateDTO = billingService.loadBillingTemplateByGuid(guid);
        }
        model.put("billingTemplateDTO", billingTemplateDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingTemplateDTO templateDTO, BindingResult errors) {
        BillingType type = templateDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String categoryGuid = templateDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择账单分类");
        }
        String subcategoryGuid = templateDTO.getSubcategoryGuid();
        if (ValidationUtils.isEmpty(subcategoryGuid)) {
            errors.rejectValue("subcategoryGuid", "subcategoryGuid", "请选择账单子分类");
        }
        String amount = templateDTO.getAmount();
        if (!ValidationUtils.isPositiveBigDecimal(amount)) {
            errors.rejectValue("amount", "amount", "请输入账单金额，数字");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingTemplate(templateDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
