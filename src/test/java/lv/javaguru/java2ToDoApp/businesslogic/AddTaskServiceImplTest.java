package lv.javaguru.java2ToDoApp.businesslogic;


import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.businesslogic.api.AddTaskService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.task.AddTaskServiceImpl;
import lv.javaguru.java2ToDoApp.businesslogic.impl.task.AddTaskValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddTaskServiceImplTest {

    @Mock
    private TaskDAO taskDAO;
    @Mock
    private AddTaskValidator addTaskValidator;

    @InjectMocks
    private AddTaskService service = new AddTaskServiceImpl();

    @Test
    public void addNewTaskTest() {
        doReturn(Lists.newArrayList()).when(addTaskValidator).validate("buy milk", "false", "2017-09-09", "LOW");
        Response result = service.addTask("buy milk", "false", "2017-09-09", "LOW");

        assertThat(result.isSuccess(), is(true));
        verify(taskDAO).save(any(Task.class));
    }
}
