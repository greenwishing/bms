package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.open.OpenUser;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.exception.BmsException;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.service.BillingService;
import cn.greenwishing.bms.service.OSSService;
import cn.greenwishing.bms.service.WeAppService;
import cn.greenwishing.bms.utils.MD5Utils;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private OSSService ossService;

    @Override
    public OpenUserDTO register(WeAppUserInfo userInfo) {
        String openid = userInfo.getOpenid();
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        if (openUser == null) {
            openUser = new OpenUser(openid);
        }
        String avatarUrl = userInfo.getAvatarUrl();
        try {
            avatarUrl = ossService.upload(avatarUrl);
            userInfo.setAvatarUrl(avatarUrl);
        } catch (Exception ignored) {}
        openUser.update(userInfo);
        userRepository.save(openUser);
        User user = openUser.user();
        if (user == null) {
            user = new User(openid, MD5Utils.EMPTY);
            user.updateUsername(userInfo.getNickName());
            userRepository.saveOrUpdate(user);
            try {
                generateDefaultData(user.guid());
            } catch (Exception ignored) {}
        }
        openUser.update(user);
        return new OpenUserDTO(openUser);
    }

    @Override
    public void bindOpenUser(String openid, String userGuid) {
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        User user = userRepository.findByGuid(User.class, userGuid);
        openUser.update(user);
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

    @Override
    public UserDTO loadUserByOpenid(String openid) {
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        if (openUser == null) {
            return null;
        }
        User user = openUser.user();
        if (user == null) {
            return null;
        }
        return new UserDTO(user);
    }

    @Override
    public List<OpenUserDTO> loadAllOpenUser() {
        List<OpenUser> users = userRepository.findAll(OpenUser.class);
        return OpenUserDTO.valueOf(users);
    }

    @Override
    public void uploadAvatar(String openid) {
        OpenUser openUser = userRepository.findOpenUserByOpenid(openid);
        if (openUser == null) {
            return;
        }
        String avatarUrl = openUser.avatar();
        if (ValidationUtils.isEmpty(avatarUrl)) {
            return;
        }
        avatarUrl = ossService.upload(avatarUrl);
        openUser.updateAvatar(avatarUrl);
        userRepository.saveOrUpdate(openUser);
    }

    @Override
    public void updateAccount(String userGuid, String account, String password) {
        User user = userRepository.findByGuid(User.class, userGuid);
        if (user == null) {
            throw new BmsException("尚未注册");
        }
        User exists = userRepository.findUserByAccount(account);
        if (exists != null && !exists.equals(user)) {
            throw new BmsException("帐号已被他人使用，请更换后重试");
        }
        user.modifyAccount(account, MD5Utils.md5(password));
        userRepository.saveOrUpdate(user);
    }
}
