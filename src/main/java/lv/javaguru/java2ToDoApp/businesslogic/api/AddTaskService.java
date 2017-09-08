package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.common.Response;

public interface AddTaskService {
    Response addTask(String title, String done, String dueDate, String priority);
}
