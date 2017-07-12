package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.Date;
import java.util.List;

public class BusinessLogicImpl implements BusinessLogic {

    private TaskDatabase taskDao;

    public BusinessLogicImpl(TaskDatabase taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public boolean addTask(Integer id, String title, boolean done, Date dueDate, Priority priority) {
        return false;
    }

    @Override
    public boolean removeTask(Task task) {
        return false;
    }

    @Override
    public Task getTaskById(Integer id) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }
}
