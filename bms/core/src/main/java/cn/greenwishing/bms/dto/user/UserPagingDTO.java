package cn.greenwishing.bms.dto.user;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.AbstractPagingDTO;
import cn.greenwishing.bms.utils.paging.UserPaging;

import java.util.List;

/**
 * @author Frank wu
 * @date 2016/7/13
 */
public class UserPagingDTO extends AbstractPagingDTO<UserDTO, UserPaging> {

    private String key;

    @Override
    public UserPaging toPaging() {
        return new UserPaging(currentPage, pageSize, userGuid, key);
    }

    @Override
    protected void convertList(UserPaging paging) {
        List<User> users = paging.getList();
        this.list = UserDTO.toDTOs(users);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
