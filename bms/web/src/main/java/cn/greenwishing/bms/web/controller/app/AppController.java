package cn.greenwishing.bms.web.controller.app;

import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;
import cn.greenwishing.bms.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/8/13
 */
@Controller
@RequestMapping("/system/app/")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping("list")
    public String list(ModelMap model) {
        List<OAuthAppDTO> apps = appService.loadApps();
        model.put("apps", apps);
        return "app/app_list";
    }
}
