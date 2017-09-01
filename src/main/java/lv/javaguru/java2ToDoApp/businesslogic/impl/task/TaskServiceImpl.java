package lv.javaguru.java2ToDoApp.businesslogic.impl.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.TaskService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public void create(Task task) {
        taskDAO.save(task);
    }
}
