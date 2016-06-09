package cn.greenwishing.bms.web.controller.metro;

import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.SimpleMetroLineStation;
import cn.greenwishing.bms.dto.metro.StationDTO;
import cn.greenwishing.bms.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
@Controller
@RequestMapping("/system/metro/")
public class MetroController {

    @Autowired
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
    public ModelAndView stations(Integer metroLineId) {
        List<SimpleMetroLineStation> metroLineStations = metroService.loadSimpleStationByMetroLine(metroLineId);
        ModelMap model = new ModelMap();
        model.put("metroLineStations", metroLineStations);
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
