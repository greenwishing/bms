package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingCategoryFormController extends SimpleFormController {

    private BillingService billingService;

    public BillingCategoryFormController() {
        setCommandClass(BillingCategoryDTO.class);
        setCommandName("billingCategoryDTO");
        setFormView("billing/billing_category_form");
        setSessionForm(true);
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("types", BillingType.values());
        return data;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String guid = ServletRequestUtils.getStringParameter(request, "guid");
        if (ValidationUtils.isNotEmpty(guid)) {
            return billingService.loadBillingCategoryByGuid(guid);
        }
        return new BillingCategoryDTO();
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        BillingCategoryDTO categoryDTO = (BillingCategoryDTO) command;
        BillingType type = categoryDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String name = categoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        if (errors.hasErrors()) {
            return showForm(request, response, errors);
        }
        billingService.saveOrUpdateBillingCategory(categoryDTO);
        return new ModelAndView("redirect:list");
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
