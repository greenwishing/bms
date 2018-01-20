package cn.greenwishing.bms.web.controller.api;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.statistics.highcharts.Series;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.utils.SecurityHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
@Controller
@RequestMapping("/oauth/api/")
public class OAuthAPIController {

    @Resource
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
        return new ModelAndView(new MappingJackson2JsonView(), params);
    }

    @RequestMapping("nearest")
    public ModelAndView nearest(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "EXPEND") BillingType type) {
        String userGuid = SecurityHolder.getUserGuid();
        List<Series> series = billingService.loadNearestStatistics(size, type, userGuid);
        return new ModelAndView(new MappingJackson2JsonView(), "series", series);
    }
}
