package cn.greenwishing.bms.web.controller.app;

import cn.greenwishing.bms.domain.oauth.OAuthAuthorities;
import cn.greenwishing.bms.domain.oauth.OAuthGrantType;
import cn.greenwishing.bms.domain.oauth.OAuthResourceId;
import cn.greenwishing.bms.domain.oauth.OAuthScope;
import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;
import cn.greenwishing.bms.service.AppService;
import cn.greenwishing.bms.utils.SecurityHolder;
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
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/8/13
 */
@Controller
@RequestMapping("/system/app/")
public class AppController {

    @Resource
    private AppService appService;

    @RequestMapping("list")
    public ModelAndView list(ModelMap model) {
        String userGuid = SecurityHolder.getUserGuid();
        List<OAuthAppDTO> apps = appService.loadApps(userGuid);
        model.put("apps", apps);
        return new ModelAndView("app/app_list");
    }

    @GetMapping({"reg", "edit"})
    @ModelAttribute("appDTO")
    public ModelAndView appForm(String appId, ModelMap model) {
        OAuthAppDTO appDTO;
        if (ValidationUtils.isEmpty(appId)) {
            String userGuid = SecurityHolder.getUserGuid();
            Long appCount = appService.loadAppCount(userGuid);
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
        return new ModelAndView("app/app_form");
    }

    @PostMapping({"reg", "edit"})
    public ModelAndView saveApp(@ModelAttribute("appDTO") OAuthAppDTO appDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            appDTO.setUserGuid(SecurityHolder.getUserGuid());
            appService.saveOrUpdate(appDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
