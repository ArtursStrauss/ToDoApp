package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetTaskByIdService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class GetTaskByIdServiceImpl implements GetTaskByIdService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public Optional<Task> getTaskById(Integer id) {
        return taskDAO.getById(id);
    }
}
