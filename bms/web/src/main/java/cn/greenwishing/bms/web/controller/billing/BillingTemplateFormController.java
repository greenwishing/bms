package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingTemplateDTO;
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
import java.util.List;
import java.util.Map;

/**
 * @author Wu Fan
 */
public class BillingTemplateFormController extends SimpleFormController {

    private BillingService billingService;

    public BillingTemplateFormController() {
        setCommandClass(BillingTemplateDTO.class);
        setCommandName("billingTemplateDTO");
        setFormView("billing/billing_template_form");
        setSessionForm(true);
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("types", BillingType.values());
        List<BillingCategoryDTO> categories = billingService.loadBillingCategory();
        data.put("categories", categories);
        return data;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String guid = ServletRequestUtils.getStringParameter(request, "guid");
        if (ValidationUtils.isNotEmpty(guid)) {
            return billingService.loadBillingTemplateByGuid(guid);
        }
        return new BillingTemplateDTO();
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        BillingTemplateDTO templateDTO = (BillingTemplateDTO) command;
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
        if (errors.hasErrors()) {
            return showForm(request, response, errors);
        }
        billingService.saveOrUpdateBillingTemplate(templateDTO);
        return new ModelAndView("redirect:list");
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
