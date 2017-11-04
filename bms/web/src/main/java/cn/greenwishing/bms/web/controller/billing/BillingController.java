package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.domain.billing.BillingStatus;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.dto.statistics.highcharts.SeriesObject;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Frank wu
 */
@Controller
@RequestMapping("/system/billing/")
public class BillingController {

    @Resource
    private BillingService billingService;

    @RequestMapping("overview")
    public ModelAndView overview(ModelMap model) {
        AssetDTO asset =  billingService.loadAsset();
        model.put("types", BillingType.values());
        model.put("asset", asset);
        return new ModelAndView("billing/billing_overview");
    }

    @RequestMapping("main")
    public ModelAndView main(ModelMap model) {
        model.put("types", BillingType.values());
        return new ModelAndView("billing/billing_main");
    }

    @RequestMapping("list")
    public ModelAndView list(BillingPagingDTO pagingDTO, ModelMap model) {
        pagingDTO = billingService.loadBillingPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        model.put("types", BillingType.values());
        return new ModelAndView("billing/billing_list");
    }

    @GetMapping("record")
    @ModelAttribute("billingDTO")
    public ModelAndView form(ModelMap model) {
        List<BillingAccountDTO> accounts = billingService.loadBillingAccounts();
        Map<BillingAccountType, List<BillingAccountDTO>> accountMap = new TreeMap<>();
        Map<BillingAccountType, List<BillingAccountDTO>> loanAccountMap = new TreeMap<>();
        for (BillingAccountDTO account : accounts) {
            BillingAccountType type = account.getType();
            if (type.isLoan()) {
                List<BillingAccountDTO> loanAccountList = loanAccountMap.get(type);
                if (loanAccountList == null) {
                    loanAccountList = new ArrayList<>();
                    loanAccountMap.put(type, loanAccountList);
                }
                loanAccountList.add(account);
            } else {
                List<BillingAccountDTO> accountList = accountMap.get(type);
                if (accountList == null) {
                    accountList = new ArrayList<>();
                    accountMap.put(type, accountList);
                }
                accountList.add(account);
            }
        }
        model.put("accountMap", accountMap);
        model.put("loanAccountMap", loanAccountMap);
        model.put("billingDTO", new BillingDTO());
        return new ModelAndView("billing/billing_form");
    }

    @PostMapping("record")
    public ModelAndView save(@ModelAttribute("billingDTO") BillingDTO billingDTO, BindingResult errors) {
        String categoryGuid = billingDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String subcategoryGuid = billingDTO.getSubcategoryGuid();
        if (ValidationUtils.isEmpty(subcategoryGuid)) {
            errors.rejectValue("subcategoryGuid", "subcategoryGuid", "请选择子分类");
        }
        String name = billingDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "名称不能为空");
        }
        String amount = billingDTO.getAmount();
        if (ValidationUtils.isEmpty(amount)) {
            errors.rejectValue("amount", "amount", "金额不能为空");
        } else if (!ValidationUtils.isAllNumber(amount)) {
            errors.rejectValue("amount", "amount", "金额格式不正确");
        }
        String occurredTime = billingDTO.getOccurredTime();
        if (!ValidationUtils.isEmpty(occurredTime) && !ValidationUtils.isValidDate(occurredTime)) {
            errors.rejectValue("occurredTime", "occurredTime", "时间格式不正确");
        }
        BillingType type = billingDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "缺少参数");
        } else {
            String srcAccountGuid = billingDTO.getSrcAccountGuid();
            if (ValidationUtils.isEmpty(srcAccountGuid)) {
                errors.rejectValue("srcAccountGuid", "srcAccountGuid", "请选择账户");
            }
            if (BillingType.targetAccountNeeds().contains(type)) {
                String targetAccountGuid = billingDTO.getTargetAccountGuid();
                if (ValidationUtils.isEmpty(targetAccountGuid)) {
                    errors.rejectValue("targetAccountGuid", "targetAccountGuid", "请选择目标账户");
                }
            }
        }

        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBilling(billingDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_category", "edit_category"})
    @ModelAttribute("billingCategoryDTO")
    public ModelAndView categoryForm(String guid, ModelMap model) {
        model.put("types", BillingType.values());
        BillingCategoryDTO billingCategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingCategoryDTO = new BillingCategoryDTO();
        } else {
            billingCategoryDTO = billingService.loadBillingCategoryByGuid(guid);
        }
        model.put("billingCategoryDTO", billingCategoryDTO);
        return new ModelAndView("billing/billing_category_form");
    }

    @PostMapping({"add_category", "edit_category"})
    public ModelAndView saveCategory(@ModelAttribute("billingCategoryDTO") BillingCategoryDTO categoryDTO, BindingResult errors) {
        BillingType type = categoryDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String name = categoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingCategory(categoryDTO);
            model.put("success", true);
            model.put("redirectUrl", "categories");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_subcategory", "edit_subcategory"})
    @ModelAttribute("billingSubcategoryDTO")
    public ModelAndView subcategoryForm(String guid, ModelMap model) {
        BillingSubcategoryDTO billingSubcategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingSubcategoryDTO = new BillingSubcategoryDTO();
        } else {
            billingSubcategoryDTO = billingService.loadBillingSubcategoryByGuid(guid);
        }
        model.put("billingSubcategoryDTO", billingSubcategoryDTO);
        return new ModelAndView("billing/billing_subcategory_form");
    }

    @PostMapping({"add_subcategory", "edit_subcategory"})
    public ModelAndView saveSubcategory(@ModelAttribute("billingSubcategoryDTO") BillingSubcategoryDTO subcategoryDTO, BindingResult errors) {
        String categoryGuid = subcategoryDTO.getCategoryGuid();
        if (ValidationUtils.isEmpty(categoryGuid)) {
            errors.rejectValue("categoryGuid", "categoryGuid", "请选择分类");
        }
        String name = subcategoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingSubcategory(subcategoryDTO);
            model.put("success", true);
            model.put("redirectUrl", "subcategories?categoryGuid=" + categoryGuid);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_account", "edit_account"})
    @ModelAttribute("billingAccountDTO")
    public ModelAndView accountForm(String guid, ModelMap model) {
        model.put("types", BillingAccountType.values());
        BillingAccountDTO billingAccountDTO;
        if (ValidationUtils.isEmpty(guid)) {
            billingAccountDTO = new BillingAccountDTO();
        } else {
            billingAccountDTO = billingService.loadBillingAccountByGuid(guid);
        }
        model.put("billingAccountDTO", billingAccountDTO);
        return new ModelAndView("billing/billing_account_form");
    }

    @PostMapping({"add_account", "edit_account"})
    public ModelAndView saveAccount(@ModelAttribute("billingAccountDTO") BillingAccountDTO accountDTO, BindingResult errors) {
        BillingAccountType type = accountDTO.getType();
        if (type == null) {
            errors.rejectValue("type", "type", "请选择类型");
        }
        String name = accountDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        String balance = accountDTO.getBalance();
        if (ValidationUtils.isNotEmpty(balance) && !ValidationUtils.isAllNumber(balance)) {
            errors.rejectValue("balance", "balance", "余额必须是数字");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingService.saveOrUpdateBillingAccount(accountDTO);
            model.put("success", true);
            model.put("redirectUrl", "accounts");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("suggest_tpl")
    public ModelAndView suggestTpl(BillingType type, Integer size) {
        if (size == null) {
            size = 10;
        }
        List<SuggestTemplateDTO> tplList = billingService.loadSuggestTemplate(type, size);
        return new ModelAndView(new MappingJackson2JsonView(), new ModelMap("tplList", tplList));
    }

    @RequestMapping("delete")
    public ModelAndView delete(String guid) {
        billingService.deleteBillingByGuid(guid);
        return new ModelAndView("redirect:list");
    }

    @RequestMapping("status")
    public ModelAndView status(String guid, BillingStatus status) {
        billingService.changeStatus(guid, status);
        return new ModelAndView("redirect:list");
    }

    @RequestMapping("nearest_data")
    public ModelAndView nearestData(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "EXPEND") BillingType type) {
        List<SeriesObject> series = billingService.loadNearestStatistics(size, type);
        return new ModelAndView(new MappingJackson2JsonView(), "series", series);
    }

    @RequestMapping("data")
    public ModelAndView data(String type, String from, String to) {
        List<BillingStatistics> data = billingService.loadBillingStatistics(type, from, to);
        Map<String, Object> model = new HashMap<>();
        model.put("data", data);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("map_data")
    public ModelAndView mapData(String type, String year) {
        Map<String, Float> data = billingService.loadBillingMapData(type, year);
        Map<String, Object> model = new HashMap<>();
        model.put("data", data);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("accounts")
    public ModelAndView accounts(String dataType) {
        List<BillingAccountDTO> accounts = billingService.loadBillingAccounts();
        Map<String, Object> model = new HashMap<>();
        model.put("accounts", accounts);
        if ("json".equals(dataType)) {
            return new ModelAndView(new MappingJackson2JsonView(), model);
        }
        return new ModelAndView("billing/billing_account_list", model);
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
            return new ModelAndView(new MappingJackson2JsonView(), model);
        }
        return new ModelAndView("billing/billing_category_list", model);
    }

    @RequestMapping("subcategories")
    public ModelAndView subcategories(String categoryGuid, String dataType) {
        List<BillingSubcategoryDTO> subcategories = billingService.loadBillingSubcategory(categoryGuid);
        Map<String, Object> model = new HashMap<>();
        model.put("subcategories", subcategories);
        if ("json".equals(dataType)) {
            return new ModelAndView(new MappingJackson2JsonView(), model);
        }
        return new ModelAndView("billing/billing_subcategory_list", model);
    }

    @RequestMapping("gen")
    public ModelAndView gen() {
        List<BillingCategoryDTO> categoryDTOs = billingService.loadBillingCategory();
        if (categoryDTOs.isEmpty()) {
            billingService.generateDefaultCategory();
        }
        return null;
    }
}
