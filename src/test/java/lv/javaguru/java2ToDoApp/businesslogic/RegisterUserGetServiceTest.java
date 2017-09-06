package lv.javaguru.java2ToDoApp.businesslogic;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.businesslogic.api.RegisterUserValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.businesslogic.impl.user.UserRegistrationServiceImpl;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
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
public class RegisterUserGetServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private RegisterUserValidator registerUserValidator;

    @InjectMocks
    private UserRegistrationService service = new UserRegistrationServiceImpl();

    @Test
    public void registerNewUserTest() {
        doReturn(Maps.newHashMap()).when(registerUserValidator).validate("arturs", "test1", "test1");
        Response result = service.register("arturs", "test1", "strauss arturs", "test1");

        assertThat(result.isSuccess(), is(true));
        verify(userDAO).save(any(User.class));
    }
}
