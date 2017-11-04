package cn.greenwishing.bms.web.controller.metro;

import cn.greenwishing.bms.domain.metro.MetroLineStationStatus;
import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.MetroLineStationDTO;
import cn.greenwishing.bms.dto.metro.SimpleMetroLineStation;
import cn.greenwishing.bms.dto.metro.StationDTO;
import cn.greenwishing.bms.service.MetroService;
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

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Frank wu
 * @date 2016/6/5
 */
@Controller
@RequestMapping("/system/metro/")
public class MetroController {

    @Resource
    private MetroService metroService;

    @RequestMapping("list")
    public String list(ModelMap model) {
        List<MetroLineDTO> lines = metroService.loadMetroLines();
        model.put("lines", lines);
        return "metro/metro_line_list";
    }

    @RequestMapping("stations")
    public String stations(ModelMap model) {
        List<StationDTO> stations = metroService.loadStations();
        model.put("stations", stations);
        return "metro/station_list";
    }

    @RequestMapping("metro_line_stations")
    public ModelAndView metroLineStations(Integer metroLineId) {
        List<SimpleMetroLineStation> metroLineStations = metroService.loadSimpleStationByMetroLine(metroLineId);
        ModelMap model = new ModelMap();
        model.put("metroLineStations", metroLineStations);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("metroLineDTO")
    public String metroLineForm(String guid, ModelMap model) {
        MetroLineDTO metroLineDTO;
        if (ValidationUtils.isEmpty(guid)) {
            metroLineDTO = new MetroLineDTO();
        } else {
            metroLineDTO = metroService.loadMetroLineByGuid(guid);
        }
        model.put("metroLineDTO", metroLineDTO);
        return "metro/metro_line_form";
    }

    @PostMapping({"add", "edit"})
    public ModelAndView saveMetroLine(@ModelAttribute("metroLineDTO") MetroLineDTO metroLineDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
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
        } else {
            metroService.saveOrUpdateMetroLine(metroLineDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_station", "edit_station"})
    @ModelAttribute("stationDTO")
    public String stationForm(String guid, String lineStationGuid, ModelMap model) {
        StationDTO stationDTO;
        if (ValidationUtils.isEmpty(guid)) {
            stationDTO = new StationDTO();
        } else {
            stationDTO = metroService.loadStationByGuid(guid);
            if (ValidationUtils.isNotEmpty(lineStationGuid)) {
                MetroLineStationDTO lineStationDTO = metroService.loadMetroLineStationByGuid(lineStationGuid);
                if (lineStationDTO != null) {
                    stationDTO.setEditWithMetroLine(true);
                    stationDTO.setLineStationGuid(lineStationGuid);
                    stationDTO.setStatus(lineStationDTO.getStatus());
                    model.put("lineStationStatusList", MetroLineStationStatus.values());
                }
            }
        }
        model.put("stationDTO", stationDTO);
        model.put("hasBaiduMap", true);
        return "metro/station_form";
    }

    @PostMapping({"add_station", "edit_station"})
    public ModelAndView saveStation(@ModelAttribute("stationDTO") StationDTO stationDTO, BindingResult errors) {
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
                    StationDTO thisStation = metroService.loadStationByGuid(guid);
                    if (!name.equals(thisStation.getName())) {
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
        } else {
            metroService.saveOrUpdateStation(stationDTO);
            model.put("success", true);
            model.put("back", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
