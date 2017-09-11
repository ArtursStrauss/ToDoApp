package lv.javaguru.java2ToDoApp.core.businesslogic.task;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskUpdateService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.task.TaskUpdateServiceImpl;
import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
