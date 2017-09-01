package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetAllTasksService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class GetAllTasksServiceImpl implements GetAllTasksService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public List<Task> getAllTasks() {

        return taskDAO.getAll();
    }

    @Override
    @Transactional
    public List<Task> getAllTasksByUser(User user) {

        return taskDAO.getAllByUserId(user.getId());
    }
}
