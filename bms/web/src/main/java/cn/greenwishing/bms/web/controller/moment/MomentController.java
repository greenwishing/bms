package cn.greenwishing.bms.web.controller.moment;

import cn.greenwishing.bms.domain.moment.GoalType;
import cn.greenwishing.bms.dto.moment.MomentDTO;
import cn.greenwishing.bms.dto.moment.MomentPagingDTO;
import cn.greenwishing.bms.dto.moment.MomentTypeDTO;
import cn.greenwishing.bms.service.MomentService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.security.provider.SHA;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
@Controller
@RequestMapping("/system/moment")
public class MomentController {

    @Resource
    private MomentService momentService;

    @RequestMapping("list")
    public ModelAndView list(MomentPagingDTO pagingDTO, ModelMap model) throws Exception {
        List<MomentTypeDTO> types = momentService.loadMomentTypes();
        model.put("types", types);
        pagingDTO = momentService.loadMomentPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("moment/moment_list");
    }

    @GetMapping("types")
    public ModelAndView types(ModelMap model) throws Exception {
        List<MomentTypeDTO> types = momentService.loadMomentTypes();
        model.put("types", types);
        return new ModelAndView("moment/moment_type_list");
    }

    @GetMapping({"add_moment", "edit_moment"})
    @ModelAttribute("momentDTO")
    public ModelAndView momentForm(String guid, ModelMap model) throws Exception {
        List<MomentTypeDTO> types = momentService.loadMomentTypes();
        model.put("types", types);
        MomentDTO momentDTO;
        if (ValidationUtils.isEmpty(guid)) {
            momentDTO = new MomentDTO();
        } else {
            momentDTO = momentService.loadMomentByGuid(guid);
        }
        model.put("momentDTO", momentDTO);
        return new ModelAndView("moment/moment_form");
    }

    @PostMapping({"add_moment", "edit_moment"})
    public ModelAndView saveMoment(@ModelAttribute("momentDTO") MomentDTO momentDTO, BindingResult errors) throws Exception {
        String typeGuid = momentDTO.getTypeGuid();
        if (ValidationUtils.isEmpty(typeGuid)) {
            errors.rejectValue("typeGuid", "typeGuid", "请选择类型");
        }
        String date = momentDTO.getDate();
        if (!ValidationUtils.isValidDate(date)) {
            errors.rejectValue("date", "date", "请选择有效的日期");
        }
        String startTime = momentDTO.getStartTime();
        if (!ValidationUtils.isValidTime(startTime)) {
            errors.rejectValue("startTime", "startTime", "请选择有效的开始时间");
        }
        String endTime = momentDTO.getEndTime();
        if (!ValidationUtils.isValidTime(endTime)) {
            errors.rejectValue("endTime", "endTime", "请选择有效的结束时间");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            momentService.saveOrUpdateMoment(momentDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_moment_type", "edit_moment_type"})
    @ModelAttribute("momentTypeDTO")
    public ModelAndView typeForm(String guid, ModelMap model) throws Exception {
        model.put("types", GoalType.values());
        MomentTypeDTO momentTypeDTO;
        if (ValidationUtils.isEmpty(guid)) {
            momentTypeDTO = new MomentTypeDTO();
        } else {
            momentTypeDTO = momentService.loadMomentTypeByGuid(guid);
        }
        model.put("momentTypeDTO", momentTypeDTO);
        return new ModelAndView("moment/moment_type_form");
    }

    @PostMapping({"add_moment_type", "edit_moment_type"})
    public ModelAndView saveType(@ModelAttribute("momentTypeDTO") MomentTypeDTO momentTypeDTO, BindingResult errors) throws Exception {
        String name = momentTypeDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入名称");
        }
        GoalType type = momentTypeDTO.getGoalType();
        if (type == null) {
            errors.rejectValue("goalType", "goalType", "请选择目标时间类型");
        }
        if (GoalType.NONE != type) {
            Long goal = momentTypeDTO.getGoal();
            if (goal == null) {
                errors.rejectValue("goal", "goal", "请选择目标时间");
            }
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            momentTypeDTO.setUserGuid(SecurityHolder.getUserGuid());
            momentService.saveOrUpdateMomentType(momentTypeDTO);
            model.put("success", true);
            model.put("redirectUrl", "types");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
