package lv.javaguru.java2ToDoApp.database.api;

import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {
    Task save(Task task);

    void delete(Task task);

    void update(Task task);

    Optional<Task> getById(Long id);

    Optional<Task> getByTitle(String id);

    List<Task> getAllByUserId(Long userId);

    List<Task> getTaskListByUserAndTitle(User user, String title);

    List<Task> getAll();
}
