package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.service.BillingService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * User: Wu Fan
 * Date: 2013-11-18 22:35
 */
public class APIController extends MultiActionController {

    private BillingService billingService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String methodName = getMethodNameResolver().getHandlerMethodName(request);
        return invokeNamedMethod(methodName, request, response);
    }

    public ModelAndView month_in(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BigDecimal monthInCount = billingService.loadMonthInCount();
        return new ModelAndView(new MappingJacksonJsonView(), "monthCount", monthInCount);
    }

    public ModelAndView month_out(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BigDecimal monthOutCount = billingService.loadMonthOutCount();
        return new ModelAndView(new MappingJacksonJsonView(), "monthCount", monthOutCount);
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
