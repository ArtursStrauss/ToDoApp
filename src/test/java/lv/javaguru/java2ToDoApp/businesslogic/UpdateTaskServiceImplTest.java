package lv.javaguru.java2ToDoApp.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.businesslogic.api.UpdateTaskService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.businesslogic.impl.UpdateTaskServiceImpl;
import lv.javaguru.java2ToDoApp.businesslogic.impl.UpdateTaskValidator;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskBuilder;
import lv.javaguru.java2ToDoApp.domain.TaskUpdater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTaskServiceImplTest {
    @Mock
    private TaskDAO taskDAO;
    @Mock
    private TaskUpdater taskUpdater;
    @Mock
    private UpdateTaskValidator updateTaskValidator;


    @InjectMocks
    private UpdateTaskService service = new UpdateTaskServiceImpl();

    @Test
    public void updateTaskTest() {

        Task task = TaskBuilder.createTask(1, "buy milk", "false", "2017-09-09", "LOW");

        doReturn(Optional.of(task)).when(taskDAO).getById(task.getId());

        doReturn(new UpdateTaskValidator()).when(updateTaskValidator).validateTaskExists(task.getId());
        doReturn(new UpdateTaskValidator()).when(updateTaskValidator).validateDone("false");
        doReturn(new UpdateTaskValidator()).when(updateTaskValidator).validateDueDate(task.getDueDate().toString());
        doReturn(new UpdateTaskValidator()).when(updateTaskValidator).validatePriority(task.getPriority().toString());
        doReturn(Lists.newArrayList()).when(updateTaskValidator).validate();

        doReturn(new TaskUpdater()).when(taskUpdater).getTask(task.getId());
        doReturn(new TaskUpdater()).when(taskUpdater).updateTitle(task.getTitle());
        doReturn(new TaskUpdater()).when(taskUpdater).updateDone("false");
        doReturn(new TaskUpdater()).when(taskUpdater).updateDueDate(task.getDueDate().toString());
        doReturn(new TaskUpdater()).when(taskUpdater).updatePriority(task.getPriority().toString());
        doReturn(task).when(taskUpdater).update();

        Response result = service.updateTask(1, "buy milk", "false", "2017-09-09", "LOW");

        assertThat(result.isSuccess(), is(true));
        verify(taskDAO).update(any(Task.class));

    }
}
