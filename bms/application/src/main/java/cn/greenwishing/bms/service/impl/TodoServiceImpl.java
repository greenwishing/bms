package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.todo.Todo;
import cn.greenwishing.bms.domain.todo.TodoRepository;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.todo.TodoDTO;
import cn.greenwishing.bms.service.TodoService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
@Service("todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveOrUpdateTodo(TodoDTO todoDTO) {
        Todo todo;
        String guid = todoDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            todo = new Todo(user);
        } else {
            todo = todoRepository.findByGuid(Todo.class, guid);
        }
        todo.update(todoDTO.getContent());
        todoRepository.saveOrUpdate(todo);
    }

    @Override
    public TodoDTO loadTodoByGuid(String guid) {
        Todo todo = todoRepository.findByGuid(Todo.class, guid);
        return new TodoDTO(todo);
    }

    @Override
    public void toggleDone(String guid) {
        Todo todo = todoRepository.findByGuid(Todo.class, guid);
        todo.toggleDone();
    }

    @Override
    public void removeDone(List<String> guids) {
        List<Todo> todos = todoRepository.findByGuids(Todo.class, guids);
        for (Todo todo : todos) {
            todo.remove();
        }
        todoRepository.saveAll(todos);
    }

    @Override
    public List<TodoDTO> loadTodos() {
        Integer userId = SecurityHolder.getUserId();
        List<Todo> todos = todoRepository.findByUserId(userId);
        return TodoDTO.toDTOs(todos);
    }
}
