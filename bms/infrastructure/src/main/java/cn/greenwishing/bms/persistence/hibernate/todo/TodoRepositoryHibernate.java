package cn.greenwishing.bms.persistence.hibernate.todo;

import cn.greenwishing.bms.domain.Status;
import cn.greenwishing.bms.domain.todo.Todo;
import cn.greenwishing.bms.domain.todo.TodoRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
@Repository("todoRepository")
public class TodoRepositoryHibernate extends AbstractRepositoryHibernate implements TodoRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<Todo> findByUserId(Integer userId) {
        return getHibernateTemplate().find("from Todo t where t.user.id=? and t.status=? order by t.id desc", userId, Status.NORMAL);
    }
}
