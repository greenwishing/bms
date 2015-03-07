package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Fan
 */
public class BillingCategoryController extends MultiActionController {

    private BillingService billingService;

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BillingCategoryDTO> categories = billingService.loadBillingCategory();
        Map<String, Object> model = new HashMap<>();
        model.put("categories", categories);
        model.put("types", BillingType.values());
        return new ModelAndView("billing/billing_category_list", model);
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
