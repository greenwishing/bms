package cn.greenwishing.bms.dto.todo;

import cn.greenwishing.bms.domain.todo.TodoPaging;
import cn.greenwishing.bms.dto.AbstractPagingDTO;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class TodoPagingDTO extends AbstractPagingDTO<TodoDTO, TodoPaging> {
    @Override
    public TodoPaging toPaging() {
        return null;
    }

    @Override
    protected void convertList(TodoPaging paging) {

    }
}
