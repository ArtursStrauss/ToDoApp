package lv.javaguru.java2ToDoApp.database.api;

import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDatabase {
    void addTask(Task task);

    void removeTask(Task task);

    Optional<Task> getTaskById(Integer id);

    Optional<Task> updateTask(Task task);

    List<Task> getAllTasks();

    int getIndexByID(Integer id);
}
