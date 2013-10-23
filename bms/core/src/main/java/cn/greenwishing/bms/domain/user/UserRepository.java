package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.domain.Repository;

/**
 * @author Wu Fan
 */
public interface UserRepository extends Repository {
    User findUserByAccount(String account);
}
