package lv.javaguru.java2ToDoApp.core.businesslogic.impl.task;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskUpdateService;
import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaskUpdateServiceImpl implements TaskUpdateService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public void update(Task task){
        taskDAO.update(task);
    }
}
