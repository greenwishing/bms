package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingPagingDTO;
import cn.greenwishing.bms.dto.billing.BillingSubcategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingTemplateDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Fan
 */
public class BillingController extends MultiActionController {

    private BillingService billingService;

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

    public ModelAndView categories(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = ServletRequestUtils.getRequiredStringParameter(request, "type");
        BillingType billingType = EnumUtils.nameOf(BillingType.class, type);
        List<BillingCategoryDTO> categories = new ArrayList<>();
        if (billingType != null) {
            categories = billingService.loadBillingCategoryByType(billingType);
        }
        return new ModelAndView(new MappingJacksonJsonView(), "categories", categories);
    }

    public ModelAndView subcategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String categoryGuid = ServletRequestUtils.getRequiredStringParameter(request, "categoryGuid");
        List<BillingSubcategoryDTO> subcategories = billingService.loadBillingSubcategory(categoryGuid);
        return new ModelAndView(new MappingJacksonJsonView(), "subcategories", subcategories);
    }

    public ModelAndView templates(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BillingTemplateDTO> templates = billingService.loadBillingTemplate();
        return new ModelAndView(new MappingJacksonJsonView(), "templates", templates);
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
