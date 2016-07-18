package cn.greenwishing.bms.web.controller.oauth;

import cn.greenwishing.bms.domain.oauth.OAuthAuthorities;
import cn.greenwishing.bms.domain.oauth.OAuthGrantType;
import cn.greenwishing.bms.domain.oauth.OAuthResourceId;
import cn.greenwishing.bms.domain.oauth.OAuthScope;
import cn.greenwishing.bms.dto.oauth.OAuthClientDetailsDTO;
import cn.greenwishing.bms.service.OAuthService;
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
 * @author Wufan
 * @date 2015/11/7.
 */
@Controller
@RequestMapping(value = {"/system/client/reg", "/system/client/edit"})
@SessionAttributes("clientDTO")
public class OAuthClientFormController {

    public static final String FORM_VIEW = "oauth/client_form";

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String clientId, ModelMap model) {
        OAuthClientDetailsDTO clientDTO;
        if (ValidationUtils.isEmpty(clientId)) {
            clientDTO = new OAuthClientDetailsDTO();
        } else {
            clientDTO = oAuthService.loadOAuthClientByClientId(clientId);
        }
        model.put("clientDTO", clientDTO);
        model.put("resourceIdList", OAuthResourceId.values());
        model.put("scopeList", OAuthScope.values());
        model.put("grantTypeList", OAuthGrantType.values());
        model.put("authorityList", OAuthAuthorities.values());
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(OAuthClientDetailsDTO clientDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            oAuthService.saveOrUpdate(clientDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
