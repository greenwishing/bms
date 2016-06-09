package cn.greenwishing.bms.web.controller.metro;

import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.service.MetroService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Fan
 */
@Controller
@RequestMapping({"/system/metro/add", "/system/metro/edit"})
@SessionAttributes("metroLineDTO")
public class MetroLineFormController {

    @Autowired
    private MetroService metroService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        MetroLineDTO metroLineDTO;
        if (ValidationUtils.isEmpty(guid)) {
            metroLineDTO = new MetroLineDTO();
        } else {
            metroLineDTO = metroService.loadMetroLineByGuid(guid);
        }
        model.put("metroLineDTO", metroLineDTO);
        return "metro/metro_line_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(MetroLineDTO metroLineDTO, BindingResult errors) {
        Map<String, Object> model = new HashMap<>();
        String name = metroLineDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "名称不能为空");
        }
        String color = metroLineDTO.getColor();
        if (ValidationUtils.isEmpty(color)) {
            errors.rejectValue("color", "color", "标识颜色不能为空");
        }
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
            model.put("errors", errors.getModel());
        } else {
            metroService.saveOrUpdateMetroLine(metroLineDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
