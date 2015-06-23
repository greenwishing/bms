package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.dto.statistics.highcharts.SeriesObject;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: Wu Fan
 * Date: 2013-11-18 22:35
 */
@Controller
@RequestMapping("/api/")
public class APIController {

    @Autowired
    private BillingService billingService;

    @RequestMapping("nearest")
    public ModelAndView nearest_in(@RequestParam(defaultValue = "20")Integer size) throws Exception {
        List<SeriesObject> series = billingService.loadNearestStatistics(size);
        return new ModelAndView(new MappingJacksonJsonView(), "series", series);
    }
}
