package lv.javaguru.java2ToDoApp.businesslogic.impl.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskDeleteService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaskDeleteServiceImpl implements TaskDeleteService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public void delete(Task task) {
        taskDAO.delete(task);
    }
}
