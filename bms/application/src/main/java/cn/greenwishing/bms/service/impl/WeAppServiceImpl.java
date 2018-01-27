package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.open.OpenUser;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.service.WeAppService;
import cn.greenwishing.bms.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wufan
 * @date 2018/1/6
 */
@Service("weAppService")
public class WeAppServiceImpl implements WeAppService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private BillingService billingService;
    @Resource
    private ArticleService articleService;

    @Override
    public OpenUserDTO saveOpenUserInfo(WeAppUserInfo userInfo) {
        String openid = userInfo.getOpenid();
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        if (openUser == null) {
            openUser = new OpenUser(openid);
        }
        openUser.update(userInfo);
        userRepository.save(openUser);
        return new OpenUserDTO(openUser);
    }

    @Override
    public void bindOpenUser(String openid, String userGuid) {
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        User user = userRepository.findByGuid(User.class, userGuid);
        openUser.update(user);
    }

    @Override
    public UserDTO fastRegister(WeAppUserInfo userInfo) {
        String openid = userInfo.getOpenid();
        User user = userRepository.findUserByAccount(openid);
        if (user == null) {
            user = new User(openid, MD5Utils.EMPTY);
            user.updateUsername(userInfo.getNickName());
            userRepository.saveOrUpdate(user);
            try {
                generateDefaultData(user.guid());
            } catch (Exception ignored) {}
        }
        bindOpenUser(openid, user.guid());
        return new UserDTO(user);
    }

    @Override
    public void generateDefaultData(String userGuid) {
        // 生成默认账单分类
        billingService.generateDefaultCategory(userGuid);
        // 生成默认账户
        billingService.generateDefaultAccount(userGuid);
        // 生成默认文章分类
        articleService.generateDefaultCategory(userGuid);
    }
}
