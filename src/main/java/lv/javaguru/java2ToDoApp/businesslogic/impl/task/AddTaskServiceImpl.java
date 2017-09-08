package lv.javaguru.java2ToDoApp.businesslogic.impl.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.AddTaskService;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static lv.javaguru.java2ToDoApp.domain.TaskBuilder.createTask;

@Component
public class AddTaskServiceImpl implements AddTaskService {

    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private AddTaskValidator addTaskValidator;

    @Override
    @Transactional
    public Response addTask(String title, String done, String dueDate, String priority) {

        //List<Error> validationErrors = addTaskValidator.validate(title, done, dueDate, priority);
        //if (!validationErrors.isEmpty()) {
        //    return Response.createFailResponse(validationErrors);
        //}

        Task task = createTask()
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .build();

        taskDAO.save(task);

        return Response.createSuccessResponse();
    }
}
