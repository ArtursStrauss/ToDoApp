package lv.javaguru.java2ToDoApp.businesslogic.api.task;

import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;

import java.util.List;
import java.util.Optional;

public interface GetTasks {

    List<Task> getAllTasks();

    List<Task> getAllTasksByUser(User user);

    Optional<Task> getTaskById(Long id);
}
