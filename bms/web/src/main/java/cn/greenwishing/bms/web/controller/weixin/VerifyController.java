package cn.greenwishing.bms.web.controller.weixin;

import cn.greenwishing.bms.web.mapping.RootMappingHandler;
import cn.greenwishing.bms.web.mapping.SpecialMappingHandlerMapping;
import cn.greenwishing.bms.web.view.TextView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2018/1/6
 */
@Controller
@RequestMapping("/")
public class VerifyController {

    private static final Map<String, String> SPECIAL_CONTENT = new HashMap<>();
    private static final String FILE_PREFIX = "MP_verify_";

    @RequestMapping(FILE_PREFIX + "{code}.txt")
    public ModelAndView verify(@PathVariable String code) throws Exception {
        return new ModelAndView(new TextView(FILE_PREFIX + code, code));
    }

    @RootMappingHandler
    public ModelAndView verify(HttpServletRequest request) throws Exception {
        String requestURI = request.getRequestURI();
        String key = requestURI.replaceAll("\\/([\\w]+)(\\.txt)?", "$1$2");
        String content = SPECIAL_CONTENT.get(key);
        if (content != null) {
            return new ModelAndView(new TextView(key, content));
        }
        return null;
    }

    @RequestMapping("mapping/register")
    public ModelAndView registerRootMapping(String key, String content) throws Exception {
        SPECIAL_CONTENT.put(key, content);
        ModelMap model = new ModelMap();
        try {
            SpecialMappingHandlerMapping.register(key, this, RootMappingHandler.class);
            model.put("success", true);
            model.put("message", "the uri '/" + key + "' is enabled on server!");
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getLocalizedMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
