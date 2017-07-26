package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2ToDoApp.domain.TaskBuilder.createTask;

public class BusinessLogicImpl implements BusinessLogic {

    private TaskDatabase taskDao;
    private AddTaskValidator addTaskValidator;
    private UpdateTaskValidator updateTaskValidator;

    public BusinessLogicImpl(TaskDatabase taskDao,
                             AddTaskValidator addTaskValidator,
                             UpdateTaskValidator updateTaskValidator) {

        this.taskDao = taskDao;
        this.addTaskValidator = addTaskValidator;
        this.updateTaskValidator = updateTaskValidator;
    }

    @Override
    public Response addTask(Integer id, String title, String done, String dueDate, String priority) {

        List<Error> validationErrors = addTaskValidator.validate(id, title, done, dueDate, priority);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        Task task = createTask()
                .withId(id)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .build();

        taskDao.addTask(task);

        return Response.createSuccessResponse();
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
    public Response updateTask(Integer id, String title, String done, String dueDate, String priority) {
        List<Error> validationErrors = updateTaskValidator.validateTaskExists(id)
                .validateDone(done)
                .validateDueDate(dueDate)
                .validatePriority(priority)
                .validate();

        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        Optional<Task> task = this.getTaskById(id);

        if (!title.isEmpty()) {
            task.get().setTitle(title);
        }

        if (!done.isEmpty()) {
            task.get().setDone(done);
        }

        if (!dueDate.isEmpty()) {
            task.get().setDueDate(dueDate);
        }

        if (!priority.isEmpty()) {
            task.get().setPriority(priority);
        }

        taskDao.updateTask(task.get());

        return Response.createSuccessResponse();
    }

    @Override
    public List<Task> getAllTasks() {

        return taskDao.getAllTasks();
    }
}
