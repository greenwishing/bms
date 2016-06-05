package cn.greenwishing.bms.web.controller.metro;

import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.StationDTO;
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
@RequestMapping({"/system/metro/add_station", "/system/metro/edit_station"})
@SessionAttributes("stationDTO")
public class StationFormController {

    @Autowired
    private MetroService metroService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        StationDTO stationDTO;
        if (ValidationUtils.isEmpty(guid)) {
            stationDTO = new StationDTO();
        } else {
            stationDTO = metroService.loadStationByGuid(guid);
        }
        model.put("stationDTO", stationDTO);
        return "metro/station_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(StationDTO stationDTO, BindingResult errors) {
        Map<String, Object> model = new HashMap<>();
        String name = stationDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "名称不能为空");
        } else {
            boolean exists = metroService.checkStationNameExists(name);
            if (exists) {
                String guid = stationDTO.getGuid();
                if (ValidationUtils.isEmpty(guid)) {
                    errors.rejectValue("name", "name", "名称[" + name + "]已存在");
                } else {
                    MetroLineDTO metroLineDTO = metroService.loadMetroLineByGuid(guid);
                    if (!name.equals(metroLineDTO.getName())) {
                        errors.rejectValue("name", "name", "名称[" + name + "]已存在");
                    }
                }
            }
        }
        String longitude = stationDTO.getLongitude();
        if (ValidationUtils.isNotEmpty(longitude) && !ValidationUtils.isPositiveBigDecimal(longitude)) {
            errors.rejectValue("longitude", "longitude", "经度必须为数字");
        }
        String latitude = stationDTO.getLatitude();
        if (ValidationUtils.isNotEmpty(latitude) && !ValidationUtils.isPositiveBigDecimal(latitude)) {
            errors.rejectValue("latitude", "latitude", "纬度必须为数字");
        }
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
            model.put("errors", errors.getModel());
        } else {
            metroService.saveOrUpdateStation(stationDTO);
            model.put("success", true);
            model.put("redirectUrl", "stations");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
