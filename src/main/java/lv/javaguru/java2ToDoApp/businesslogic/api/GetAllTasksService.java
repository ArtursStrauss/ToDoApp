package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;

public interface GetAllTasksService {
    public List<Task> getAllTasks();
}
