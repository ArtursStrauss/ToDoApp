package lv.javaguru.java2ToDoApp.core.businesslogic.task;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskDeleteService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.task.TaskDeleteServiceImpl;
import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import lv.javaguru.java2ToDoApp.core.domain.TaskBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TaskDeleteServiceImplTest {
    @Mock
    private TaskDAO taskDAO;
    private Task task;

    @InjectMocks
    private TaskDeleteService service = new TaskDeleteServiceImpl();

    @Test
    public void deleteTaskTest(){

        task = TaskBuilder.createTask()
                .withId(new Long(1))
                .withTitle("buy milk")
                .withDone("false")
                .withDueDate("2017-09-09")
                .withPriority("LOW")
                .withUserId(new Long(1))
                .build();

        service.delete(task);

        verify(taskDAO).delete(any(Task.class));
    }
}
