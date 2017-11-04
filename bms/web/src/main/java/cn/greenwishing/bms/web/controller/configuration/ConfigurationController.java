package cn.greenwishing.bms.web.controller.configuration;

import cn.greenwishing.bms.dto.configuration.ConfigurationDTO;
import cn.greenwishing.bms.service.ConfigurationService;
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
 * @date 2017/5/13
 */
@Controller
@RequestMapping("/system/configuration")
public class ConfigurationController {

    @Resource
    private ConfigurationService configurationService;

    @RequestMapping("list")
    public ModelAndView list(ModelMap model) throws Exception {
        List<ConfigurationDTO> configurations = configurationService.loadConfigurations();
        model.put("configurations", configurations);
        return new ModelAndView("configuration/configuration_list");
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("configurationDTO")
    public ModelAndView configurationForm(String guid, ModelMap model) throws Exception {
        ConfigurationDTO configurationDTO;
        if (ValidationUtils.isEmpty(guid)) {
            configurationDTO = new ConfigurationDTO();
        } else {
            configurationDTO = configurationService.loadConfigurationByGuid(guid);
        }
        model.put("configurationDTO", configurationDTO);
        return new ModelAndView("configuration/configuration_form");
    }

    @PostMapping({"add", "edit"})
    public ModelAndView saveConfiguration(@ModelAttribute("configurationDTO") ConfigurationDTO configurationDTO, BindingResult errors) throws Exception {
        String key = configurationDTO.getKey();
        if (ValidationUtils.isEmpty(key)) {
            errors.rejectValue("key", "key", "请填写键");
        }
        String value = configurationDTO.getValue();
        if (ValidationUtils.isEmpty(value)) {
            errors.rejectValue("value", "value", "请填写值");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            configurationService.saveOrUpdateConfiguration(configurationDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("refresh_cache")
    public ModelAndView refreshCache() throws Exception {
        ModelMap model = new ModelMap();
        try {
            configurationService.refreshCache();
            model.put("success", true);
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping("send_mail")
    public ModelAndView sendMailForm() throws Exception {
        return new ModelAndView("configuration/mail_send_form");
    }

    @PostMapping("send_mail")
    public ModelAndView sendMail(String email, String subject, String content) throws Exception {
        ModelMap model = new ModelMap();
        try {
            configurationService.sendMail(email, subject, content);
            model.put("success", true);
            model.put("tips", "发送成功！");
            model.put("back", true);
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
