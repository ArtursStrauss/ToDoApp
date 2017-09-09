package lv.javaguru.java2ToDoApp.businesslogic.user;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.businesslogic.api.RegistrationFormValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.user.UserRegistrationServiceImpl;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    private RegistrationForm registrationForm;

    @Mock
    private UserDAO userDAO;
    @Mock
    private RegistrationFormValidator registrationFormValidator;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @InjectMocks
    private UserRegistrationService service = new UserRegistrationServiceImpl();

    @Before
    public void setUp() throws Exception {

        registrationForm = new RegistrationForm();

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");
    }

    @Test
    public void registerNewUserTest() {
        doReturn(Maps.newHashMap()).when(registrationFormValidator).validate(registrationForm);
        Response response = service.register(registrationForm);

        assertThat(response.isSuccess(), is(true));
        verify(bCryptPasswordEncoder).encode(registrationForm.getPassword());
        verify(userDAO).save(any(User.class));
    }
}
