package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.UpdateTaskService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UpdateTaskServiceImpl implements UpdateTaskService {

    @Autowired
    private UpdateTaskValidator updateTaskValidator;
    @Autowired
    private TaskUpdater taskUpdater;
    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public Response updateTask(Long id, String title, String done, String dueDate, String priority) {
        List<Error> validationErrors = updateTaskValidator.validateTaskExists(id)
                .validateDone(done)
                .validateDueDate(dueDate)
                .validatePriority(priority)
                .validate();

        //if (!validationErrors.isEmpty()) {
        //    return Response.createFailResponse(validationErrors);
        //}

        Task task = taskUpdater.getTask(id)
                .updateTitle(title)
                .updateDone(done)
                .updateDueDate(dueDate)
                .updatePriority(priority)
                .update();

        taskDAO.update(task);

        return Response.createSuccessResponse();
    }
}
