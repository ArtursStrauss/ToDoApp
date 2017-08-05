package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;

public interface UpdateTaskService {
    public Response updateTask(Integer id, String title, String done, String dueDate, String priority);
}
