package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.todo.Todo;
import cn.greenwishing.bms.domain.todo.TodoPaging;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class TodoQueryHelper extends AbstractQueryHelper<Todo, TodoPaging> {

    public TodoQueryHelper(HibernateTemplate hibernateTemplate, TodoPaging paging) {
        super(hibernateTemplate, paging);
    }

    @Override
    public String getResultHql() {
        return null;
    }
}
