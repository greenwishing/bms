package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.dto.billing.BillingSubcategoryDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wufan
 * @date 2015/3/7.
 */
public class BillingSubcategoryFormController extends SimpleFormController {

    private BillingService billingService;

    public BillingSubcategoryFormController() {
        setCommandClass(BillingSubcategoryDTO.class);
        setCommandName("billingSubcategoryDTO");
        setFormView("billing/billing_subcategory_form");
        setSessionForm(true);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String guid = ServletRequestUtils.getStringParameter(request, "guid");
        if (ValidationUtils.isNotEmpty(guid)) {
            return billingService.loadBillingSubcategoryByGuid(guid);
        }
        return new BillingSubcategoryDTO();
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        BillingSubcategoryDTO subcategoryDTO = (BillingSubcategoryDTO) command;
        String categoryGuid = subcategoryDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String name = subcategoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        if (errors.hasErrors()) {
            return showForm(request, response, errors);
        }
        billingService.saveOrUpdateBillingSubcategory(subcategoryDTO);
        return new ModelAndView("redirect:list?categoryGuid=" + categoryGuid);
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
