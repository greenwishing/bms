package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.dto.billing.BillingSubcategoryDTO;
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
@RequestMapping({"/system/billing/add_subcategory", "/system/billing/edit_subcategory"})
@SessionAttributes("billingSubcategoryDTO")
public class BillingSubcategoryFormController {

    public static final String FORM_VIEW = "billing/billing_subcategory_form";

    @Autowired
    private BillingService billingService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        BillingSubcategoryDTO billingSubcategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingSubcategoryDTO = new BillingSubcategoryDTO();
        } else {
            billingSubcategoryDTO = billingService.loadBillingSubcategoryByGuid(guid);
        }
        model.put("billingSubcategoryDTO", billingSubcategoryDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(BillingSubcategoryDTO subcategoryDTO, BindingResult errors) {
        String categoryGuid = subcategoryDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String name = subcategoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingSubcategory(subcategoryDTO);
            model.put("success", true);
            model.put("redirectUrl", "subcategories?categoryGuid=" + categoryGuid);
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
