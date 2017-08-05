package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.Optional;

public interface GetTaskByIdService {
    public Optional<Task> getTaskById(Integer id);
}
