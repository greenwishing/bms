package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.dto.statistics.highcharts.SeriesObject;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.List;

/**
 * User: Wu Fan
 * Date: 2013-11-18 22:35
 */
@Controller
@RequestMapping("/api")
public class APIController {

    @Autowired
    private BillingService billingService;

    @RequestMapping("nearest")
    public ModelAndView nearest(@RequestParam(defaultValue = "20") Integer size) {
        ModelMap model = new ModelMap();
        try {
            List<SeriesObject> series = billingService.loadNearestStatistics(size);
            model.put("success", true);
            model.put("series", series);
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getMessage());
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
