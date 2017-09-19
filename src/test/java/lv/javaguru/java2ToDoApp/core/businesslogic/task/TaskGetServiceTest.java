package lv.javaguru.java2ToDoApp.core.businesslogic.task;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.task.TaskGetServiceImpl;
import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import lv.javaguru.java2ToDoApp.core.domain.TaskBuilder;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskGetServiceTest {

    private Task task;
    private User user;

    @Mock
    private TaskDAO taskDAO;

    @InjectMocks
    private TaskGetService service = new TaskGetServiceImpl();

    @Before
    public void setUp() throws Exception {
        task = createTask();

        user = createUser();
    }

    @Test
    public void getAllTasksTest() {

        doReturn(Lists.newArrayList(task)).when(taskDAO).getAll();

        List<Task> result = service.getAllTasks();

        assertThat(result, is(Lists.newArrayList(task)));
        verify(taskDAO).getAll();
    }

    @Test
    public void getAllTasksByUserTest() {

        doReturn(Lists.newArrayList(task)).when(taskDAO).getAllByUserId(new Long(1));

        List<Task> result = service.getAllTasksByUser(user);

        assertThat(result, is(Lists.newArrayList(task)));
        verify(taskDAO).getAllByUserId(new Long(1));

    }

    @Test
    public void getAllTasksByIdTest() {

        doReturn(Optional.of(task)).when(taskDAO).getById(new Long(1));

        Optional<Task> result = service.getTaskById(new Long(1));

        assertThat(result.isPresent(), is(true));
        verify(taskDAO).getById(new Long(1));

    }

    @Test
    public void getTaskListByUserAndTitleTest() {

        doReturn(Lists.newArrayList(task)).when(taskDAO).getTaskListByUserAndTitle(user, "buy milk");

        List<Task> result = service.getTaskListByUserAndTitle(user, "buy milk");

        assertThat(result, is(Lists.newArrayList(task)));
        verify(taskDAO).getTaskListByUserAndTitle(user, "buy milk");
    }

    private Task createTask() {
        return TaskBuilder.createTask()
                .withId(new Long(1))
                .withTitle("buy milk")
                .withDone("false")
                .withDueDate("2017-09-09")
                .withPriority("LOW")
                .withUserId(new Long(1))
                .build();
    }

    private User createUser() {
        return UserBuilder.createUser()
                .withId(new Long(1))
                .build();
    }
}
