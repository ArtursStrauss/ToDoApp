package lv.javaguru.java2ToDoApp.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.businesslogic.api.task.GetTasks;
import lv.javaguru.java2ToDoApp.businesslogic.impl.task.GetTasksImpl;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetAllTasksServiceImplTest {
    @Mock
    private TaskDAO taskDAO;
    @InjectMocks
    private GetTasks service = new GetTasksImpl();

    @Test
    public void getAllTasksTest() {

        Task task = TaskBuilder.createTask(new Long(1), "buy milk", "false", "2017-09-09", "LOW",new Long(1));

        doReturn(Lists.newArrayList(task)).when(taskDAO).getAll();

        List<Task> result = service.getAllTasks();

        assertThat(result, is(Lists.newArrayList(task)));
        verify(taskDAO).getAll();
    }
}
