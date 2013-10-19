package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wu Fan
 */
public class BillingController extends MultiActionController {

    private BillingService billingService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String methodName = getMethodNameResolver().getHandlerMethodName(request);
        return invokeNamedMethod(methodName, request, response);
    }

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BillingDTO> billingDTOs = billingService.loadBillings();
        return new ModelAndView("billing/billing_list", "billingDTOs", billingDTOs);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String guid = ServletRequestUtils.getRequiredStringParameter(request, "guid");
        billingService.deleteBillingByGuid(guid);
        return new ModelAndView("redirect:list");
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
