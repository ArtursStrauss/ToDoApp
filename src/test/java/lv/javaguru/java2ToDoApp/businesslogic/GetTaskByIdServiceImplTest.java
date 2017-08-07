package lv.javaguru.java2ToDoApp.businesslogic;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetTaskByIdService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.GetTaskByIdServiceImpl;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetTaskByIdServiceImplTest {

    @Mock
    private TaskDAO taskDAO;
    @InjectMocks
    private GetTaskByIdService service = new GetTaskByIdServiceImpl();

    @Test
    public void getTaskByIdTest() {

        Task task = TaskBuilder.createTask(1, "buy milk", "false", "2017-09-09", "LOW");

        doReturn(Optional.of(task)).when(taskDAO).getById(task.getId());

        Optional<Task> result = service.getTaskById(task.getId());

        assertThat(result.get(), is(task));
        verify(taskDAO).getById(task.getId());

    }
}
