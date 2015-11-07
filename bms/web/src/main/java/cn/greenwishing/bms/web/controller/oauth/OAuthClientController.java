package cn.greenwishing.bms.web.controller.oauth;

import cn.greenwishing.bms.dto.oauth.OAuthClientDetailsDTO;
import cn.greenwishing.bms.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
@Controller
@RequestMapping("/system/client/")
public class OAuthClientController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping("list")
    public String list(ModelMap model) {
        List<OAuthClientDetailsDTO> clients = oAuthService.loadOAuthClients();
        model.put("clients", clients);
        return "oauth/client_list";
    }
}
