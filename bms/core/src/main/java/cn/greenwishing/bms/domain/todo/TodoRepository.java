package cn.greenwishing.bms.domain.todo;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
public interface TodoRepository extends Repository {
    List<Todo> findByUserId(Integer userId);
}
