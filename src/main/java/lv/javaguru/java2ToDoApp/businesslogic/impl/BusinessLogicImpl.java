package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BusinessLogicImpl implements BusinessLogic {

    private TaskDatabase taskDao;

    public BusinessLogicImpl(TaskDatabase taskDao) {

        this.taskDao = taskDao;
    }

    @Override
    public void addTask(Integer id, String title, Boolean done, Date dueDate, Priority priority) {

        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDone(done);
        task.setDueDate(dueDate);
        task.setPriority(priority);

        taskDao.addTask(task);

    }

    @Override
    public boolean removeTask(Task task) {
        Optional<Task> foundTask = taskDao.getTaskById(task.getId());
        if (foundTask.isPresent()) {

            taskDao.removeTask(foundTask.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {

        return taskDao.getTaskById(id);
    }

    @Override
    public void updateTask(Task task) {

        taskDao.updateTask(task);
    }

    @Override
    public List<Task> getAllTasks() {

        return taskDao.getAllTasks();
    }
}
