package lv.javaguru.java2ToDoApp.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.businesslogic.impl.AddTaskValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.BusinessLogicImpl;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.businesslogic.impl.UpdateTaskValidator;
import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.junit.Before;
import org.junit.Test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

public class BusinessLogicImplTest {

    private TaskDatabase taskDao;
    private AddTaskValidator addTaskValidator;
    private UpdateTaskValidator updateTaskValidator;
    private BusinessLogic service;

    @Before
    public void init() {
        taskDao = mock(TaskDatabase.class);
        addTaskValidator = mock(AddTaskValidator.class);
        updateTaskValidator = mock(UpdateTaskValidator.class);
        service = new BusinessLogicImpl(taskDao, addTaskValidator,updateTaskValidator);
    }

    @Test
    public void addNewTaskInList() {

        doReturn(Lists.newArrayList()).when(addTaskValidator).validate(1,"buy milk","false","2017-09-09","LOW");
        Response result = service.addTask(1,"buy milk","false","2017-09-09","LOW");

        assertThat(result.isSuccess(), is(true));
        verify(taskDao).addTask(any(Task.class));
    }

    @Test
    public void removeTakFromList() {

        Date dueDate = dateParser("2017-09-09");
        Task task = new Task(1, "buy Milk", false, dueDate, Priority.LOW);

        doReturn(Optional.empty()).when(taskDao).getTaskById(1);
        doReturn(null).when(taskDao).removeTask(task);

        service.addTask(1,"buy milk","false","2017-09-09","LOW");
        service.removeTask(task);

        assertThat(taskDao.getTaskById(1).isPresent(), is(false));
        verify(taskDao).removeTask(task);
    }

    @Test
    public void editTaskInList(){

        doReturn(Lists.newArrayList()).when(addTaskValidator).validate(1,"buy milk","false","2017-09-09","LOW");
        Response result = service.addTask(1,"buy milk","false","2017-09-09","LOW");

        assertThat(result.isSuccess(), is(true));
        verify(taskDao).addTask(any(Task.class));
    }

    private Date dateParser(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
