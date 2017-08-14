package lv.javaguru.java2ToDoApp.businesslogic.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetTaskByIdService;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class GetTaskByIdServiceImpl implements GetTaskByIdService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public Optional<Task> getTaskById(Long id) {

        return taskDAO.getById(id);
    }
}
