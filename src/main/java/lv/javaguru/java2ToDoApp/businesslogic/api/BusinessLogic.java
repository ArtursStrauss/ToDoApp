package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BusinessLogic {
    boolean addTask(Integer id,
                    String title,
                    boolean done,
                    Date dueDate,
                    Priority priority);

    boolean removeTask(Task task);

    Optional<Task> getTaskById(Integer id);

    Task update(Task task);

    List<Task> getAllTasks();
}
