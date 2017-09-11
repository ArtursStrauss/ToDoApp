package lv.javaguru.java2ToDoApp.core.database;


import lv.javaguru.java2ToDoApp.core.config.SpringAppConfig;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@Transactional
public class UserDaoImplTest {

    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private UserDAO userDAO;

    @Before
    public void init() {
        databaseUtil.cleanDatabase();
    }

    @Test
    public void testCreate() {
        User user = create("unit-test", "Unit Test", "test123");

        assertThat(user.getId(), is(notNullValue()));

        Optional<User> userFromDB = userDAO.getById(user.getId());

        assertThat(userFromDB.isPresent(), is(true));
        assertEquals(user.getId(), userFromDB.get().getId());
        assertEquals(user.getLogin(), userFromDB.get().getLogin());
        assertEquals(user.getFullName(), userFromDB.get().getFullName());
        assertEquals(user.getPassword(), userFromDB.get().getPassword());

    }

    @Test
    public void testDelete() throws Exception {

        User user = create("unit-test", "Unit Test", "test123");
        Optional<User> userCreated = userDAO.getById(user.getId());
        assertThat(userCreated.isPresent(), is(true));

        userDAO.delete(user);
        userCreated = userDAO.getById(user.getId());
        assertThat(userCreated.isPresent(), is(false));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = create("unit-test", "Unit Test", "test123");

        assertThat(user.getId(), is(notNullValue()));

        user.setFullName("Unit Test-Modify");

        userDAO.update(user);
        Optional<User> userFromDB = userDAO.getById(user.getId());

        assertThat(userFromDB.isPresent(), is(true));
        assertEquals(user.getId(), userFromDB.get().getId());
        assertEquals(user.getLogin(), userFromDB.get().getLogin());
        assertEquals(user.getFullName(), userFromDB.get().getFullName());
        assertEquals(user.getPassword(), userFromDB.get().getPassword());
    }

    @Test
    public void testGetById() throws Exception {
        User user = create("unit-test", "Unit Test", "test123");

        Optional<User> userFromDB = userDAO.getById(user.getId());

        assertThat(userFromDB.isPresent(), is(true));
        assertEquals(user.getId(), userFromDB.get().getId());
        assertEquals(user.getLogin(), userFromDB.get().getLogin());
        assertEquals(user.getFullName(), userFromDB.get().getFullName());
        assertEquals(user.getPassword(), userFromDB.get().getPassword());
    }

    @Test
    public void testGetByLogin() throws Exception {
        User user = create("unit-test", "Unit Test", "test123");

        Optional<User> userFromDB = userDAO.getByLogin(user.getLogin());

        assertThat(userFromDB.isPresent(), is(true));
        assertEquals(user.getId(), userFromDB.get().getId());
        assertEquals(user.getLogin(), userFromDB.get().getLogin());
        assertEquals(user.getFullName(), userFromDB.get().getFullName());
        assertEquals(user.getPassword(), userFromDB.get().getPassword());
    }

    private User create(String login, String fullName, String password) {

        User user = UserBuilder.createUser()
                .withLogin(login)
                .withFullName(fullName)
                .withPassword(password)
                .build();

        return userDAO.save(user);
    }
}
