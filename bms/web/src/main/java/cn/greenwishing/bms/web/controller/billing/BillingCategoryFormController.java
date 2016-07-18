package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
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
 * @author Wufan
 * @date 2015/3/7.
 */
@Controller
@RequestMapping({"/system/billing/add_category", "/system/billing/edit_category"})
@SessionAttributes("billingCategoryDTO")
public class BillingCategoryFormController {

    public static final String FORM_VIEW = "billing/billing_category_form";

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        model.put("types", BillingType.values());
        BillingCategoryDTO billingCategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingCategoryDTO = new BillingCategoryDTO();
        } else {
            billingCategoryDTO = billingService.loadBillingCategoryByGuid(guid);
        }
        model.put("billingCategoryDTO", billingCategoryDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingCategoryDTO categoryDTO, BindingResult errors) {
        BillingType type = categoryDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String name = categoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingCategory(categoryDTO);
            model.put("success", true);
            model.put("redirectUrl", "categories");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
