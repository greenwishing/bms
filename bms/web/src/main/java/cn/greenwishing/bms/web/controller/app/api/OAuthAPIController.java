package cn.greenwishing.bms.web.controller.app.api;

import cn.greenwishing.bms.dto.statistics.highcharts.SeriesObject;
import cn.greenwishing.bms.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
@Controller
@RequestMapping("/oauth/api/")
public class OAuthAPIController {

    @Autowired
    private BillingService billingService;

    @RequestMapping("redirect")
    public ModelAndView redirect(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = request.getParameter(name);
            params.put(name, value);
        }
        return new ModelAndView(new MappingJacksonJsonView(), params);
    }

    @RequestMapping("nearest")
    public ModelAndView nearest(@RequestParam(defaultValue = "20") Integer size) {
        List<SeriesObject> series = billingService.loadNearestStatistics(size);
        return new ModelAndView(new MappingJacksonJsonView(), "series", series);
    }
}
