package cn.greenwishing.bms.dto.todo;

import cn.greenwishing.bms.domain.Status;
import cn.greenwishing.bms.domain.todo.Todo;
import cn.greenwishing.bms.utils.JodaUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class TodoDTO {

    private String guid;
    private String content;
    private boolean done;
    private String doneTime;
    private Status status;

    public TodoDTO() {

    }

    public TodoDTO(Todo todo) {
        this.guid = todo.guid();
        this.content = todo.content();
        this.done = todo.done();
        this.doneTime = JodaUtils.dateTimeToString(todo.doneTime());
        this.status = todo.status();
    }

    public static List<TodoDTO> toDTOs(List<Todo> todos) {
        List<TodoDTO> todoDTOs = new ArrayList<>();
        for (Todo todo : todos) {
            TodoDTO todoDTO = new TodoDTO(todo);
            todoDTOs.add(todoDTO);
        }
        return todoDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public Status getStatus() {
        return status;
    }
}
