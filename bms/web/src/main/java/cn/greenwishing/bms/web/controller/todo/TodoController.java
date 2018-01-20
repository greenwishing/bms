package cn.greenwishing.bms.web.controller.todo;

import cn.greenwishing.bms.dto.todo.TodoDTO;
import cn.greenwishing.bms.service.TodoService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
@Controller
@RequestMapping("/system/todo")
public class TodoController {

    @Resource
    private TodoService todoService;

    @RequestMapping("list")
    public ModelAndView list(ModelMap model) throws Exception {
        List<TodoDTO> todos = todoService.loadTodos();
        model.put("todos", todos);
        return new ModelAndView("todo/todo_list");
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("todoDTO")
    public ModelAndView todoForm(String guid, ModelMap model) throws Exception {
        TodoDTO todoDTO;
        if (ValidationUtils.isEmpty(guid)) {
            todoDTO = new TodoDTO();
        } else {
            todoDTO = todoService.loadTodoByGuid(guid);
        }
        model.put("todoDTO", todoDTO);
        return new ModelAndView("todo/todo_form");
    }

    @PostMapping({"add", "edit"})
    public ModelAndView todoSave(TodoDTO todoDTO, BindingResult errors) throws Exception {
        String content = todoDTO.getContent();
        if (ValidationUtils.isEmpty(content)) {
            errors.rejectValue("content", "content", "请填写内容");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            todoDTO.setUserGuid(SecurityHolder.getUserGuid());
            todoService.saveOrUpdateTodo(todoDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("done")
    public ModelAndView toggleDone(String guid, ModelMap model) throws Exception {
        try {
            todoService.toggleDone(guid);
            model.put("success", true);
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("remove_done")
    public ModelAndView removeDone(String[] guids, ModelMap model) throws Exception {
        try {
            if (guids != null) {
                todoService.removeDone(Arrays.asList(guids));
            }
            model.put("success", true);
        } catch (Exception e) {
            model.put("success", false);
            model.put("message", e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
