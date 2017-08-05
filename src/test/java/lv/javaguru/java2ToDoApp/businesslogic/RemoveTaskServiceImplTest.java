package lv.javaguru.java2ToDoApp.businesslogic;

import lv.javaguru.java2ToDoApp.businesslogic.api.RemoveTaskService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.RemoveTaskServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveTaskServiceImplTest {
    @Mock
    private TaskDAO taskDAO;


    @InjectMocks
    private RemoveTaskService service = new RemoveTaskServiceImpl();

    @Test
    public void removeTaskTest(){

        Optional<Task> task = Optional.of(TaskBuilder.createTask(1,"buy milk", "false", "2017-09-09", "LOW"));

        doReturn(task).when(taskDAO).getById(task.get().getId());
        Boolean result = service.removeTask(task.get());

        assertThat(result, is(true));

        verify(taskDAO).getById(task.get().getId());
        verify(taskDAO).delete(any(Task.class));
    }
}
