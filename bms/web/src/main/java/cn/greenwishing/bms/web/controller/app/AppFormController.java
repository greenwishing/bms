package cn.greenwishing.bms.web.controller.app;

import cn.greenwishing.bms.domain.oauth.OAuthAuthorities;
import cn.greenwishing.bms.domain.oauth.OAuthGrantType;
import cn.greenwishing.bms.domain.oauth.OAuthResourceId;
import cn.greenwishing.bms.domain.oauth.OAuthScope;
import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;
import cn.greenwishing.bms.service.AppService;
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

/**
 * User: Wufan
 * Date: 2016/8/13
 */
@Controller
@RequestMapping(value = {"/system/app/reg", "/system/app/edit"})
@SessionAttributes("appDTO")
public class AppFormController {

    public static final String FORM_VIEW = "app/app_form";

    @Autowired
    private AppService appService;


    @RequestMapping(method = RequestMethod.GET)
    public String form(String appId, ModelMap model) {
        OAuthAppDTO appDTO;
        if (ValidationUtils.isEmpty(appId)) {
            Long appCount = appService.loadAppCount();
            if (appCount >= 3) {
                throw new RuntimeException("一个用户最多创建3个应用，你已创建了" + appCount + "个应用");
            }
            appDTO = new OAuthAppDTO();
        } else {
            appDTO = appService.loadAppByAppId(appId);
        }
        model.put("appDTO", appDTO);
        model.put("resourceIdList", OAuthResourceId.values());
        model.put("scopeList", OAuthScope.values());
        model.put("grantTypeList", OAuthGrantType.values());
        model.put("authorityList", OAuthAuthorities.values());
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(OAuthAppDTO appDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            appService.saveOrUpdate(appDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
