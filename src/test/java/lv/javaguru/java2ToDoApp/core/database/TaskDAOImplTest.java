package lv.javaguru.java2ToDoApp.core.database;

import lv.javaguru.java2ToDoApp.core.config.SpringAppConfig;
import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static lv.javaguru.java2ToDoApp.core.domain.TaskBuilder.createTask;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@Transactional
public class TaskDAOImplTest {

    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private UserDAO userDAO;

    private User user;

    @Before
    public void init() {
        databaseUtil.cleanDatabase();
        user = createUser();
    }

    @Test
    public void testCreate() throws Exception {
        Task task = create("Clean room", "false", "2017-09-09", "LOW");

        assertThat(task.getId(), is(notNullValue()));

        Optional<Task> taskFromDB = taskDAO.getById(task.getId());

        assertThat(taskFromDB.isPresent(), is(true));
        assertEquals(task.getId(), taskFromDB.get().getId());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDueDate(), taskFromDB.get().getDueDate());
        assertEquals(task.getPriority(), taskFromDB.get().getPriority());
    }


    @Test
    public void testDelete() throws Exception {

        Task task = create("Clean room", "false", "2017-09-09", "LOW");
        List<Task> tasks = taskDAO.getAll();
        assertThat(tasks.size(), is(1));

        taskDAO.delete(task);
        tasks = taskDAO.getAll();
        assertThat(tasks.size(), is(0));

        Optional<Task> taskFromDB = taskDAO.getById(task.getId());
        assertThat(taskFromDB.isPresent(), is(false));
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = create("Clean room", "false", "2017-09-09", "LOW");

        assertThat(task.getId(), is(notNullValue()));

        task.setTitle("Clean room2");

        taskDAO.update(task);
        Optional<Task> taskFromDB = taskDAO.getById(task.getId());

        assertThat(taskFromDB.isPresent(), is(true));
        assertEquals(task.getId(), taskFromDB.get().getId());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDueDate(), taskFromDB.get().getDueDate());
        assertEquals(task.getPriority(), taskFromDB.get().getPriority());
    }

    @Test
    public void testGetAll() throws Exception {
        Task task1 = create("Clean room", "false", "2017-09-09", "LOW");
        Task task2 = create("Clean second room", "false", "2017-09-10", "LOW");

        List<Task> tasks = taskDAO.getAll();

        assertThat(tasks.size(), is(2));
        assertThat(tasks.contains(task1), is(true));
        assertThat(tasks.contains(task2), is(true));
    }

    @Test
    public void testGetById() throws Exception {
        Task task = create("Clean room", "false", "2017-09-09", "LOW");

        Optional<Task> taskFromDB = taskDAO.getById(task.getId());

        assertThat(taskFromDB.isPresent(), is(true));
        assertEquals(task.getId(), taskFromDB.get().getId());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDueDate(), taskFromDB.get().getDueDate());
        assertEquals(task.getPriority(), taskFromDB.get().getPriority());
    }

    @Test
    public void testGetByTitle() throws Exception {
        Task task = create("Clean room", "false", "2017-09-09", "LOW");

        Optional<Task> taskFromDB = taskDAO.getByTitle(task.getTitle());

        assertThat(taskFromDB.isPresent(), is(true));
        assertEquals(task.getId(), taskFromDB.get().getId());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDueDate(), taskFromDB.get().getDueDate());
        assertEquals(task.getPriority(), taskFromDB.get().getPriority());
    }

    @Test
    public void testGetAllByUserId() throws Exception {
        Task task1 = create("Clean room", "false", "2017-09-09", "LOW");
        Task task2 = create("Clean second room", "false", "2017-09-10", "LOW");

        List<Task> tasks = taskDAO.getAllByUserId(user.getId());

        assertThat(tasks.size(), is(2));
        assertThat(tasks.contains(task1), is(true));
        assertThat(tasks.contains(task2), is(true));
    }

    @Test
    public void testGetTaskListByUserAndTitle() throws Exception {
        Task task1 = create("Clean room", "false", "2017-09-09", "LOW");
        Task task2 = create("Clean second room", "false", "2017-09-10", "LOW");

        List<Task> tasks = taskDAO.getTaskListByUserAndTitle(user, "room");

        assertThat(tasks.size(), is(2));
        assertThat(tasks.contains(task1), is(true));
        assertThat(tasks.contains(task2), is(true));
    }

    private Task create(String title, String done, String dueDate, String priority) {

        Task task = createTask()
                .withId(null)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .withUserId(user.getId())
                .build();
        return taskDAO.save(task);
    }

    private User createUser() {

        User user = UserBuilder.createUser()
                .withLogin("unit-test")
                .withFullName("Unit Test")
                .withPassword("test123")
                .build();

        return userDAO.save(user);
    }
}
