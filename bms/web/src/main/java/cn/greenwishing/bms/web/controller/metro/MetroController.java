package cn.greenwishing.bms.web.controller.metro;

import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.StationDTO;
import cn.greenwishing.bms.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
