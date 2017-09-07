package lv.javaguru.java2ToDoApp.businesslogic;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.businesslogic.api.RegistrationFormValidator;
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
    private RegistrationFormValidator registrationFormValidator;
    @Mock
    private User user;

    @InjectMocks
    private UserRegistrationService service = new UserRegistrationServiceImpl();

    @Test
    public void registerNewUserTest() {
        doReturn(Maps.newHashMap()).when(registrationFormValidator).validate("arturs", "test1", "test1");
        user = service.register(user);

      //  assertThat(any(user ), is(true));
        verify(userDAO).save(any(User.class));
    }
}
