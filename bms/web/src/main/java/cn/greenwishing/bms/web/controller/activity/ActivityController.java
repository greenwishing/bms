package cn.greenwishing.bms.web.controller.activity;

import cn.greenwishing.bms.dto.activity.ActivityDTO;
import cn.greenwishing.bms.dto.activity.ActivityPagingDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanBudgetDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanDTO;
import cn.greenwishing.bms.service.ActivityService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.StringUtils;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
@Controller
@RequestMapping("/system/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @RequestMapping("list")
    public ModelAndView list(ActivityPagingDTO pagingDTO, ModelMap model) {
        pagingDTO.setUserGuid(SecurityHolder.getUserGuid());
        pagingDTO = activityService.loadActivityPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("activity/activity_list");
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("activityDTO")
    public ModelAndView activityForm(String guid, ModelMap model) {
        ActivityDTO activityDTO;
        if (ValidationUtils.isEmpty(guid)) {
            activityDTO = new ActivityDTO();
        } else {
            activityDTO = activityService.loadActivityByGuid(guid);
        }
        model.put("activityDTO", activityDTO);
        return new ModelAndView("activity/activity_form");
    }

    @PostMapping({"add", "edit"})
    public ModelAndView saveActivity(@ModelAttribute("activityDTO") ActivityDTO activityDTO, BindingResult errors) {
        String name = activityDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请填写活动名称");
        }
        String dateFrom = activityDTO.getDateFrom();
        if (!ValidationUtils.isValidDate(dateFrom)) {
            errors.rejectValue("dateFrom", "dateFrom", "请选择活动开始日期");
        }
        String dateTo = activityDTO.getDateTo();
        if (!ValidationUtils.isValidDate(dateTo)) {
            errors.rejectValue("dateTo", "dateTo", "请选择活动结束日期");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            activityDTO.setUserGuid(SecurityHolder.getUserGuid());
            activityService.saveOrUpdateActivity(activityDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("view")
    public ModelAndView preview(String guid, ModelMap model) {
        ActivityDTO activity = activityService.loadActivityByGuid(guid);
        model.put("activity", activity);
        model.put("login", SecurityHolder.getUserId() != null);
        model.put("loginUserGuid", SecurityHolder.getUserGuid());
        return new ModelAndView("activity/activity_view");
    }

    @RequestMapping("plans")
    public ModelAndView plans(@RequestParam("activityGuid") String activityGuid, ModelMap model) {
        List<ActivityPlanDTO> plans = activityService.loadActivityPlans(activityGuid);
        model.put("success", true);
        model.put("plans", plans);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"plan_add", "plan_edit"})
    @ModelAttribute("planDTO")
    public ModelAndView planForm(String guid, ModelMap model) {
        ActivityPlanDTO planDTO;
        if (ValidationUtils.isEmpty(guid)) {
            planDTO = new ActivityPlanDTO();
        } else {
            planDTO = activityService.loadActivityPlanByGuid(guid);
        }
        model.put("planDTO", planDTO);
        return new ModelAndView("activity/activity_plan_form");
    }

    @PostMapping({"plan_add", "plan_edit"})
    public ModelAndView savePlan(@ModelAttribute("planDTO") ActivityPlanDTO planDTO, BindingResult errors) {
        String name = planDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请填写计划名称");
        }
        String dateFrom = planDTO.getDateFrom();
        if (!ValidationUtils.isValidDate(dateFrom)) {
            errors.rejectValue("dateFrom", "dateFrom", "请选择计划开始日期");
        }
        String dateTo = planDTO.getDateTo();
        if (!ValidationUtils.isValidDate(dateTo)) {
            errors.rejectValue("dateTo", "dateTo", "请选择计划结束日期");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            planDTO.setUserGuid(SecurityHolder.getUserGuid());
            activityService.saveOrUpdateActivityPlan(planDTO);
            String planGuid = planDTO.getGuid();
            if (ValidationUtils.isEmpty(planGuid)) {
                // 新建返回活动主页
                model.put("redirectUrl", "view?guid=" + planDTO.getActivityGuid());
            } else {
                // 编辑返回计划页
                model.put("redirectUrl", "plan_view?activityGuid=" + planDTO.getActivityGuid() + "&planGuid=" + planGuid);
            }
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("plan_view")
    public ModelAndView planView(@RequestParam("planGuid") String planGuid, ModelMap model) {
        ActivityPlanDTO plan = activityService.loadActivityPlanByGuid(planGuid);
        model.put("plan", plan);
        model.put("login", SecurityHolder.getUserId() != null);
        model.put("loginUserGuid", SecurityHolder.getUserGuid());
        return new ModelAndView("activity/activity_plan_view");
    }

    @RequestMapping("budgets")
    public ModelAndView budgets(@RequestParam("planGuid") String planGuid, ModelMap model) {
        List<ActivityPlanBudgetDTO> budgets = activityService.loadActivityPlanBudgets(planGuid);
        model.put("success", true);
        model.put("budgets", budgets);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"budget_add", "budget_edit"})
    @ModelAttribute("budgetDTO")
    public ModelAndView budgetForm(String guid, ModelMap model) {
        ActivityPlanBudgetDTO budgetDTO;
        if (ValidationUtils.isEmpty(guid)) {
            budgetDTO = new ActivityPlanBudgetDTO();
        } else {
            budgetDTO = activityService.loadActivityPlanBudgetByGuid(guid);
        }
        model.put("budgetDTO", budgetDTO);
        return new ModelAndView("activity/activity_plan_budget_form");
    }

    @PostMapping({"budget_add", "budget_edit"})
    public ModelAndView saveBudget(@ModelAttribute("budgetDTO") ActivityPlanBudgetDTO budgetDTO, BindingResult errors) {
        String name = budgetDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请填写计划名称");
        }
        String amount = budgetDTO.getAmount();
        if (ValidationUtils.isNotEmpty(amount) && !ValidationUtils.isPositiveBigDecimal(amount)) {
            errors.rejectValue("amount", "amount", "请输入预算金额");
        }
        String actualAmount = budgetDTO.getActualAmount();
        if (ValidationUtils.isNotEmpty(actualAmount) && !ValidationUtils.isPositiveBigDecimal(actualAmount)) {
            errors.rejectValue("actualAmount", "actualAmount", "请输入实际金额");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            budgetDTO.setUserGuid(SecurityHolder.getUserGuid());
            activityService.saveOrUpdateActivityPlanBudget(budgetDTO);
            model.put("success", true);
            model.put("redirectUrl", "plan_view?activityGuid=" + budgetDTO.getActivityGuid() + "&planGuid=" + budgetDTO.getPlanGuid());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
