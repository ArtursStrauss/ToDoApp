package lv.javaguru.java2ToDoApp.database;

import lv.javaguru.java2ToDoApp.config.SpringAppConfig;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static lv.javaguru.java2ToDoApp.domain.TaskBuilder.createTask;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TaskDAOImplTest {

    private DatabaseUtil databaseUtil;
    private TaskDAO taskDAO;

    @Before
    public void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        taskDAO = applicationContext.getBean(TaskDAO.class);
        databaseUtil = applicationContext.getBean(DatabaseUtil.class);
        databaseUtil.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        Task task = create("Clean room","false","2017-09-09","LOW");
        assertThat(task.getId(), is(notNullValue()));

        Optional<Task> taskFromDB = taskDAO.getById(task.getId());

        assertThat(taskFromDB.isPresent(), is(true));
        assertEquals(task.getId(), taskFromDB.get().getId());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDueDate(), taskFromDB.get().getDueDate());
        assertEquals(task.getPriority(), taskFromDB.get().getPriority());
    }


    @Test
    public void testGetAll() throws Exception {
        Task task1 = create("Clean room","false","2017-09-09","LOW");
        Task task2 = create("Clean second room","false","2017-09-10","LOW");

        List<Task> tasks = taskDAO.getAll();

        assertThat(tasks.size(), is(2));
        assertThat(tasks.contains(task1), is(true));
        assertThat(tasks.contains(task2), is(true));
    }

    @Test
    public void testDelete() throws Exception {

        Task task = create("Clean room","false","2017-09-09","LOW");
        List<Task> tasks = taskDAO.getAll();
        assertThat(tasks.size(), is(1));

        taskDAO.delete(task);
        tasks = taskDAO.getAll();
        assertThat(tasks.size(), is(0));

        Optional<Task> taskFromDB = taskDAO.getById(task.getId());
        assertThat(taskFromDB.isPresent(), is(false));
    }

    private Task create(String title, String done, String dueDate, String priority) {

        Task task = createTask()
                .withId(null)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .build();
        return taskDAO.save(task);
    }
}
