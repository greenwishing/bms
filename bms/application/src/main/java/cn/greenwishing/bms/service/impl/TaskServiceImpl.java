package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * User: Wufan
 * Date: 2015/6/15.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Override
    public void testTask() {
        System.out.println("executing task...");
    }
}
