package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Fan
 */
public class BillingFormController extends SimpleFormController {

    private BillingService billingService;

    public BillingFormController() {
        setCommandClass(BillingDTO.class);
        setCommandName("billingDTO");
        setFormView("billing/billing_form");
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
        return new BillingDTO();
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        BillingDTO billingDTO = (BillingDTO) command;
        billingService.saveOrUpdateBilling(billingDTO);
        return new ModelAndView("redirect:list");
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
