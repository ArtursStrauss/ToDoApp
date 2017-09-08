package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.common.Response;

public interface UpdateTaskService {
    public Response updateTask(Long id, String title, String done, String dueDate, String priority);
}
