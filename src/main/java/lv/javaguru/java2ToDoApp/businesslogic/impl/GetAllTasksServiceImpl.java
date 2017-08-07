package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetAllTasksService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTasksServiceImpl implements GetAllTasksService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<Task> getAllTasks() {

        return taskDAO.getAll();
    }
}
