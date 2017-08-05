package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.RemoveTaskService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveTaskServiceImpl implements RemoveTaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public boolean removeTask(Task task) {
        Optional<Task> foundTask = taskDAO.getById(task.getId());
        if (foundTask.isPresent()) {

            taskDAO.delete(foundTask.get());
            return true;
        } else {
            return false;
        }
    }
}
