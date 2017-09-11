package lv.javaguru.java2ToDoApp.businesslogic.impl.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class TaskGetServiceImpl implements TaskGetService {

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

    @Override
    @Transactional
    public Optional<Task> getTaskById(Long id) {

        return taskDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Task> getTaskListByUserAndTitle(User user, String title) {

        return taskDAO.getTaskListByUserAndTitle(user, title);
    }
}
