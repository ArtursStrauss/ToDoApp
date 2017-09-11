package lv.javaguru.java2ToDoApp.core.businesslogic.task;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskCreateService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.task.TaskCreateServiceImpl;
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
public class TaskCreateServiceTest {

    @Mock
    private TaskDAO taskDAO;

    @Mock
    private Task task;

    @InjectMocks
    private TaskCreateService service = new TaskCreateServiceImpl();

    @Test
    public void createTaskTest() {

        //doReturn(Lists.newArrayList()).when(addTaskValidator).validate("buy milk", "false", "2017-09-09", "LOW");
        service.create(task);

        // assertThat(result.isSuccess(), is(true));
        verify(taskDAO).save(any(Task.class));
    }
}
