package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.domain.open.OpenUser;
import cn.greenwishing.bms.utils.paging.UserPaging;

/**
 * @author Frank wu
 */
public interface UserRepository extends Repository {
    User findUserByAccount(String account);

    UserPaging findUserPaging(UserPaging paging);

    String findUserGuidByAppId(String appId);

    OpenUser findOpenUserByOpenid(String openid);
}
