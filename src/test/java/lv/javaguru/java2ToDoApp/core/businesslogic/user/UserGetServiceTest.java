package lv.javaguru.java2ToDoApp.core.businesslogic.user;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserGetService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.user.UserGetServiceImpl;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserGetServiceTest {
    @Mock
    private UserDAO userDAO;
    private User user;

    @InjectMocks
    private UserGetService service = new UserGetServiceImpl();

    @Test
    public void getByLoginTest() {

        user = UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("testUser")
                .build();

        doReturn(Optional.of(user)).when(userDAO).getByLogin("testUser");

        Optional<User> result = service.getByLogin("testUser");

        assertThat(result.isPresent(), is(true));
        verify(userDAO).getByLogin("testUser");
    }
}
