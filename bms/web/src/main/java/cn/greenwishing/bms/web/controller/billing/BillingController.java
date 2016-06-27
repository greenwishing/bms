package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingStatus;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.dto.billing.BillingCategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingPagingDTO;
import cn.greenwishing.bms.dto.billing.BillingSubcategoryDTO;
import cn.greenwishing.bms.dto.billing.BillingTemplateDTO;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Fan
 */
@Controller
@RequestMapping("/system/billing/")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @RequestMapping("list")
    public String list(BillingPagingDTO pagingDTO, ModelMap model) {
        pagingDTO = billingService.loadBillingPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        model.put("types", BillingType.values());
        return "billing/billing_list";
    }

    @RequestMapping("delete")
    public String delete(String guid) {
        billingService.deleteBillingByGuid(guid);
        return "redirect:list";
    }

    @RequestMapping("status")
    public String status(String guid, BillingStatus status) {
        billingService.changeStatus(guid, status);
        return "redirect:list";
    }

    @RequestMapping("statistics")
    public String statistics(ModelMap model) {
        model.put("types", BillingType.values());
        return "billing/billing_statistics";
    }

    @RequestMapping("data")
    public ModelAndView data(String type, String group, String from, String to) {
        List<BillingStatistics> data = billingService.loadBillingStatistics(type, group, from, to);
        Map<String, Object> model = new HashMap<>();
        model.put("data", data);
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }

    @RequestMapping("categories")
    public ModelAndView categories(String type, String dataType) {
        BillingType billingType = EnumUtils.nameOf(BillingType.class, type);
        List<BillingCategoryDTO> categories;
        if (billingType != null) {
            categories = billingService.loadBillingCategoryByType(billingType);
        } else {
            categories = billingService.loadBillingCategory();
        }
        Map<String, Object> model = new HashMap<>();
        model.put("categories", categories);
        if ("json".equals(dataType)) {
            return new ModelAndView(new MappingJacksonJsonView(), model);
        }
        return new ModelAndView("billing/billing_category_list", model);
    }

    @RequestMapping("subcategories")
    public ModelAndView subcategories(String categoryGuid, String dataType) {
        List<BillingSubcategoryDTO> subcategories = billingService.loadBillingSubcategory(categoryGuid);
        Map<String, Object> model = new HashMap<>();
        model.put("subcategories", subcategories);
        if ("json".equals(dataType)) {
            return new ModelAndView(new MappingJacksonJsonView(), model);
        }
        return new ModelAndView("billing/billing_subcategory_list", model);
    }

    @RequestMapping("templates")
    public ModelAndView templates(String dataType) {
        List<BillingTemplateDTO> templates = billingService.loadBillingTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("templates", templates);
        if ("json".equals(dataType)) {
            return new ModelAndView(new MappingJacksonJsonView(), model);
        }
        return new ModelAndView("billing/billing_template_list", model);
    }
}
