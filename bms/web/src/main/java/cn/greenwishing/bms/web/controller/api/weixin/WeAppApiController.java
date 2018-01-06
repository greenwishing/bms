package cn.greenwishing.bms.web.controller.api.weixin;

import cn.greenwishing.bms.api.weixin.weapp.JSCode2SessionRequest;
import cn.greenwishing.bms.api.weixin.weapp.JSCode2SessionResponse;
import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.service.UserService;
import cn.greenwishing.bms.service.WeAppService;
import cn.greenwishing.bms.utils.MD5Utils;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.web.controller.api.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;

/**
 * @author Wufan
 * @date 2018/1/6
 */
@Controller
@RequestMapping("/api/weixin/weapp")
public class WeAppApiController {

    @Resource
    private UserService userService;
    @Resource
    private WeAppService weAppService;

    /**
     * 更新小程序用户资料
     */
    @RequestMapping("user")
    public ModelAndView updateUserInfo(String code, WeAppUserInfo userInfo) {
        ApiResult result;
        try {
            JSCode2SessionResponse response = new JSCode2SessionRequest(code).execute();
            userInfo.setOpenid(response.getOpenid());
            OpenUserDTO openUserDTO = weAppService.saveOpenUserInfo(userInfo);
            result = ApiResult.success()
                    .add("openid", response.getOpenid())
                    .add("userGuid", openUserDTO.getUserGuid());
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序用户登录
     */
    @RequestMapping("login")
    public ModelAndView login(String openid, String account, String password) {
        ApiResult result;
        try {
            UserDTO user = userService.findByAccount(account);
            if (user == null) {
                result = ApiResult.fail(-1, String.format("帐号“%s”不存在", account));
            } else {
                String md5Password = MD5Utils.md5(password);
                if (!md5Password.equalsIgnoreCase(user.getPassword())) {
                    result = ApiResult.fail(-1, "密码错误");
                } else {
                    String userGuid = user.getGuid();
                    weAppService.bindOpenUser(openid, userGuid);
                    result = ApiResult.success()
                            .add("userGuid", userGuid);
                }
            }
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序用户帐号密码注册
     */
    @RequestMapping("register")
    public ModelAndView register(String openid, UserDTO userDTO) {
        ApiResult result;
        try {
            String account = userDTO.getAccount();
            String password = userDTO.getPassword();
            String retypePassword = userDTO.getRetypePassword();
            UserDTO user = userService.findByAccount(account);
            if (user != null) {
                result = ApiResult.fail(-1, String.format("帐号“%s”已被别人用啦！", account));
            } else if (ValidationUtils.isEmpty(password)){
                result = ApiResult.fail(-1, "还是设置一个密码吧！");
            } else if (!password.equalsIgnoreCase(retypePassword)) {
                result = ApiResult.fail(-1, "密码都没弄对啊！");
            } else {
                user = userService.saveOrUpdateUser(userDTO);
                String userGuid = user.getGuid();
                weAppService.bindOpenUser(openid, userGuid);
                result = ApiResult.success()
                        .add("userGuid", userGuid);
            }
        } catch (Exception e) {
            result = ApiResult.fail(-1, e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }

    /**
     * 小程序用户快速注册（使用微信资料）
     */
    @RequestMapping("fastRegister")
    public ModelAndView register(WeAppUserInfo userInfo) {
        ApiResult result;
        if (ValidationUtils.isEmpty(userInfo.getOpenid())) {
            result = ApiResult.fail(-1, "缺少参数openid");
        } else {
            try {
                UserDTO user = weAppService.fastRegister(userInfo);
                result = ApiResult.success()
                        .add("userGuid", user.getGuid());
            } catch (Exception e) {
                result = ApiResult.fail(-1, e.getMessage());
            }
        }
        return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
    }
}
