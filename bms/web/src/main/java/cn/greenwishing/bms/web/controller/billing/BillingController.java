package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.BillingPagingDTO;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
        BillingPagingDTO billingPagingDTO = new BillingPagingDTO();
        bind(request, billingPagingDTO);
        BillingPagingDTO pagingDTO = billingService.loadBillingPaging(billingPagingDTO);
        Map<String, Object> model = new HashMap<>();
        model.put("pagingDTO", pagingDTO);
        model.put("types", BillingType.values());
        return new ModelAndView("billing/billing_list", model);
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
