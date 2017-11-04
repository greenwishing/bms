package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.oauth.App;
import cn.greenwishing.bms.domain.oauth.AppRepository;
import cn.greenwishing.bms.domain.oauth.UserApp;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;
import cn.greenwishing.bms.service.AppService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @Resource
    private AppRepository appRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public List<OAuthAppDTO> loadApps() {
        String userGuid = SecurityHolder.getUserGuid();
        List<App> list = appRepository.findUserApps(userGuid);
        return OAuthAppDTO.toDTOs(list);
    }

    @Override
    public OAuthAppDTO loadAppByAppId(String appId) {
        App app = findAppByAppId(appId);
        return app == null ? null : new OAuthAppDTO(app);
    }

    private App findAppByAppId(String appId) {
        return appRepository.findByAppId(appId);
    }

    @Override
    public void saveOrUpdate(OAuthAppDTO appDTO) {
        boolean isNew = false;
        String userGuid = SecurityHolder.getUserGuid();
        App app;
        String appId = appDTO.getAppId();
        if (ValidationUtils.isNotEmpty(appId)) {
            app = findAppByAppId(appId);
            appDTO.update(app);
            appRepository.saveOrUpdate(app);
        } else {
            isNew = true;
            app = new App();
            appDTO.update(app);
            appRepository.save(app);
        }

        if (isNew) {
            User user = userRepository.findByGuid(User.class, userGuid);
            UserApp userApp = new UserApp(user, app);
            userRepository.saveOrUpdate(userApp);
        }
    }

    @Override
    public Long loadAppCount() {
        String userGuid = SecurityHolder.getUserGuid();
        return appRepository.findAppCount(userGuid);
    }
}
