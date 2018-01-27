package cn.greenwishing.bms.web.controller.billing;

import cn.greenwishing.bms.domain.billing.BillingAccountType;
import cn.greenwishing.bms.domain.billing.BillingStatus;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.domain.statistics.BillingStatistics;
import cn.greenwishing.bms.dto.billing.*;
import cn.greenwishing.bms.dto.statistics.highcharts.Series;
import cn.greenwishing.bms.dto.statistics.mobiscroll.Wheel;
import cn.greenwishing.bms.dto.statistics.tree.TreeNode;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.web.validator.BillingValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        String userGuid = SecurityHolder.getUserGuid();
        pagingDTO.setUserGuid(userGuid);
        pagingDTO = billingService.loadBillingPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        List<TreeNode> nodes = billingService.loadBillingTreeNodes(userGuid);
        model.put("nodes", nodes);
        return new ModelAndView("billing/billing_list");
    }

    @GetMapping("record")
    @ModelAttribute("billingDTO")
    public ModelAndView form(BillingType type) {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingAccountDTO> accounts = billingService.loadBillingAccounts(userGuid);
        Map<BillingAccountType, List<BillingAccountDTO>> accountMap = new TreeMap<>();
        Map<BillingAccountType, List<BillingAccountDTO>> loanAccountMap = new TreeMap<>();
        for (BillingAccountDTO account : accounts) {
            BillingAccountType accountType = account.getType();
            if (accountType.isLoan()) {
                List<BillingAccountDTO> loanAccountList = loanAccountMap.get(accountType);
                if (loanAccountList == null) {
                    loanAccountList = new ArrayList<>();
                    loanAccountMap.put(accountType, loanAccountList);
                }
                loanAccountList.add(account);
            } else {
                List<BillingAccountDTO> accountList = accountMap.get(accountType);
                if (accountList == null) {
                    accountList = new ArrayList<>();
                    accountMap.put(accountType, accountList);
                }
                accountList.add(account);
            }
        }
        ModelMap model = new ModelMap();
        model.put("accountMap", accountMap);
        model.put("loanAccountMap", loanAccountMap);
        model.put("billingDTO", new BillingDTO(type));
        return new ModelAndView("billing/billing_form", model);
    }

    @PostMapping("record")
    public ModelAndView save(@ModelAttribute("billingDTO") BillingDTO billingDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        new BillingValidator().validate(billingDTO, errors);
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            billingDTO.setUserGuid(SecurityHolder.getUserGuid());
            billingService.saveOrUpdateBilling(billingDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("detail")
    public ModelAndView detail(String guid, ModelMap model) {
        BillingDTO billingDTO = billingService.loadBillingByGuid(guid);
        model.put("billingDTO", billingDTO);
        return new ModelAndView("billing/billing_detail", model);
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
            categoryDTO.setUserGuid(SecurityHolder.getUserGuid());
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
            accountDTO.setUserGuid(SecurityHolder.getUserGuid());
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

    @RequestMapping("wheels")
    public ModelAndView wheels() {
        ModelMap model = new ModelMap();
        String userGuid = SecurityHolder.getUserGuid();
        List<Wheel> wheels = billingService.loadBillingWheels(userGuid);
        model.put("wheels", wheels);
        return new ModelAndView(new MappingJackson2JsonView(), model);
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
        String userGuid = SecurityHolder.getUserGuid();
        List<Series> series = billingService.loadNearestStatistics(size, type, userGuid);
        return new ModelAndView(new MappingJackson2JsonView(), "series", series);
    }

    @RequestMapping("data")
    public ModelAndView data(String type, String from, String to) {
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingStatistics> data = billingService.loadBillingStatistics(type, from, to, userGuid);
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
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingAccountDTO> accounts = billingService.loadBillingAccounts(userGuid);
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
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategoryDTO> categories;
        if (billingType != null) {
            categories = billingService.loadBillingCategoryByType(billingType, userGuid);
        } else {
            categories = billingService.loadBillingCategory(userGuid);
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
        String userGuid = SecurityHolder.getUserGuid();
        List<BillingCategoryDTO> categoryDTOs = billingService.loadBillingCategory(userGuid);
        if (categoryDTOs.isEmpty()) {
            billingService.generateDefaultCategory(userGuid);
        }
        List<BillingAccountDTO> billingAccounts = billingService.loadBillingAccounts(userGuid);
        if (billingAccounts.isEmpty()) {
            billingService.generateDefaultAccount(userGuid);
        }
        return null;
    }
}
