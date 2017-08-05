package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2ToDoApp.domain.TaskBuilder.createTask;

@Component
public class BusinessLogicImpl implements BusinessLogic {

    @Autowired
    private TaskDAO taskDao;
    @Autowired
    private AddTaskValidator addTaskValidator;
    @Autowired
    private UpdateTaskValidator updateTaskValidator;
    @Autowired
    private TaskUpdater taskUpdater;

    @Override
    public Response addTask(String title, String done, String dueDate, String priority) {

        List<Error> validationErrors = addTaskValidator.validate(title, done, dueDate, priority);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        Task task = createTask()
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .build();

        taskDao.save(task);

        return Response.createSuccessResponse();
    }

    @Override
    public boolean removeTask(Task task) {
        Optional<Task> foundTask = taskDao.getById(task.getId());
        if (foundTask.isPresent()) {

            taskDao.delete(foundTask.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {

        return taskDao.getById(id);
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

        Task task = taskUpdater.getTask(id)
                .updateTitle(title)
                .updateDone(done)
                .updateDueDate(dueDate)
                .updatePriority(priority)
                .update();

        taskDao.update(task);

        return Response.createSuccessResponse();
    }

    @Override
    public List<Task> getAllTasks() {

        return taskDao.getAll();
    }
}
