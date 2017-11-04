package cn.greenwishing.bms.web.controller.user;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserStatus;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.dto.user.UserPagingDTO;
import cn.greenwishing.bms.service.UserService;
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

/**
 * @author Frank wu
 * @date 2016/7/13
 */
@Controller
@RequestMapping("/system/user/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("list")
    public String list(UserPagingDTO pagingDTO, ModelMap model) {
        pagingDTO = userService.loadUserPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return "user/user_list";
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("userDTO")
    public String form(String guid, ModelMap model) {
        model.put("statusList", UserStatus.values());
        UserDTO userDTO;
        if (ValidationUtils.isEmpty(guid)) {
            userDTO = new UserDTO();
        } else {
            userDTO = userService.loadByGuid(guid);
        }
        model.put("userDTO", userDTO);
        return "user/user_form";
    }

    @PostMapping({"add", "edit"})
    public ModelAndView save(@ModelAttribute("userDTO") UserDTO userDTO, BindingResult errors) {
        ModelMap model = new ModelMap();
        String username = userDTO.getUsername();
        if (ValidationUtils.isEmpty(username)) {
            errors.rejectValue("username", "username", "请输入姓名");
        }
        if (ValidationUtils.isEmpty(userDTO.getGuid())) {
            String account = userDTO.getAccount();
            if (ValidationUtils.isEmpty(account)) {
                errors.rejectValue("account", "account", "请输入帐号");
            } else {
                User user = userService.findByAccount(account);
                if (user != null) {
                    errors.rejectValue("account", "account", "帐号已被使用，请更换");
                }
            }
            String password = userDTO.getPassword();
            String retypePassword = userDTO.getRetypePassword();
            if (ValidationUtils.isEmpty(password)) {
                errors.rejectValue("password", "password", "请设置密码");
            } else if (ValidationUtils.isEmpty(retypePassword)) {
                errors.rejectValue("retypePassword", "retypePassword", "请再次确认密码");
            } else if (!password.equals(retypePassword)) {
                errors.rejectValue("retypePassword", "retypePassword", "两次输入密码不一致");
            }
        }
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            userService.saveOrUpdateUser(userDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
