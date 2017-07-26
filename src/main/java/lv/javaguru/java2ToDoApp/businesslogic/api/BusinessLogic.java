package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;
import java.util.Optional;

public interface BusinessLogic {
    Response addTask(Integer id,
                     String title,
                     String done,
                     String dueDate,
                     String priority);

    boolean removeTask(Task task);

    Optional<Task> getTaskById(Integer id);

    void updateTask(Task task);

    List<Task> getAllTasks();
}
