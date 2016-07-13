package cn.greenwishing.bms.web.controller.user;

import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.dto.user.UserPagingDTO;
import cn.greenwishing.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wufan
 * @date 2016/7/13
 */
@Controller
@RequestMapping("/system/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public String list(UserPagingDTO pagingDTO, ModelMap model) {
        pagingDTO = userService.loadUserPaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return "user/user_list";
    }
}
