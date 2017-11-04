package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.todo.TodoDTO;

import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
public interface TodoService {
    void saveOrUpdateTodo(TodoDTO todoDTO);

    TodoDTO loadTodoByGuid(String guid);

    void toggleDone(String guid);

    void removeDone(List<String> guids);

    List<TodoDTO> loadTodos();
}
