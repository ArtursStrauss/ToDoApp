package lv.javaguru.java2ToDoApp.core.businesslogic.user;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserChangePasswordService;
import lv.javaguru.java2ToDoApp.core.businesslogic.impl.user.UserChangePasswordServiceImpl;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserChangePasswordServiceTest {

    private User user;
    private ChangePasswordForm changePasswordForm;

    @Mock
    private UserDAO userDAO;
    @Mock
    private ChangePasswordFormValidation changePasswordFormValidation;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserChangePasswordService service = new UserChangePasswordServiceImpl();

    @Before
    public void setUp() throws Exception {

        changePasswordForm = new ChangePasswordForm();

        changePasswordForm.setLogin("userTest");
        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test1234");
        changePasswordForm.setConfirmationPassword("test1234");

        user = createUser();
    }

    @Test
    public void changeTest() {

        doReturn(Optional.of(user)).when(userDAO).getByLogin(user.getLogin());
        doReturn(Maps.newHashMap()).when(changePasswordFormValidation).validate(changePasswordForm, user);
        Response response = service.change(changePasswordForm);

        assertThat(response.isSuccess(), is(true));
        verify(userDAO).getByLogin(user.getLogin());
        verify(bCryptPasswordEncoder).encode(changePasswordForm.getNewPassword());
        verify(userDAO).save(any(User.class));

    }

    private User createUser() {
        return UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("userTest")
                .withPassword("test123")
                .build();
    }
}
