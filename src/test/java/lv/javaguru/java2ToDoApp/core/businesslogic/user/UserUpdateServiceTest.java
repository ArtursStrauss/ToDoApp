package lv.javaguru.java2ToDoApp.core.businesslogic.user;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserUpdateService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.user.UserUpdateServiceImpl;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserUpdateServiceTest {

    @Mock
    private UserDAO userDAO;
    private User user;

    @InjectMocks
    private UserUpdateService service = new UserUpdateServiceImpl();

    @Test
    public void updateTest(){

        user = UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("testUser")
                .build();

        service.update(user);

        verify(userDAO).update(any(User.class));

    }
}
