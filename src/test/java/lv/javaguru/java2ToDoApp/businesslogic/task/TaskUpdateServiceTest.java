package lv.javaguru.java2ToDoApp.businesslogic.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskUpdateService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.task.TaskUpdateServiceImpl;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskUpdateServiceTest {
    @Mock
    private TaskDAO taskDAO;
    @Mock
    private Task task;

    @InjectMocks
    private TaskUpdateService service = new TaskUpdateServiceImpl();

    @Test
    public void deleteTaskTest(){

        service.update(task);

        verify(taskDAO).update(any(Task.class));
    }
}
