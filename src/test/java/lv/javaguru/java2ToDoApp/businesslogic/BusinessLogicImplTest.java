package lv.javaguru.java2ToDoApp.businesslogic;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.businesslogic.impl.BusinessLogicImpl;
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
import static org.mockito.Mockito.*;

public class BusinessLogicImplTest {

    private TaskDatabase taskDao;
    private BusinessLogic businessLogic;

    @Before
    public void init() {
        taskDao = mock(TaskDatabase.class);
        businessLogic = new BusinessLogicImpl(taskDao);
    }

    @Test
    public void addNewTaskInList() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = new Date();
        try {
            dueDate = dateFormat.parse("2017-09-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Task task = new Task(1, "buy ofMilk", false, dueDate, Priority.LOW);

        doReturn(Optional.of(task)).when(taskDao).getTaskById(1);
        businessLogic.addTask(task.getId(), task.getTitle(), task.isDone(), task.getDueDate(), task.getPriority());

        assertEquals(taskDao.getTaskById(1).get(), task);
        verify(taskDao).addTask(task);
    }
}
