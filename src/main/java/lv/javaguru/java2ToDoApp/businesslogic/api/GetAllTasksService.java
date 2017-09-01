package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;

import java.util.List;

public interface GetAllTasksService {
    List<Task> getAllTasks();

    List<Task> getAllTasksByUser(User user);
}
