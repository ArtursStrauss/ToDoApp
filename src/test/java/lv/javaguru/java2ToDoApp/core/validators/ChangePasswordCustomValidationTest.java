package lv.javaguru.java2ToDoApp.core.validators;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordCustomValidation;
import lv.javaguru.java2ToDoApp.core.validators.impl.ChangePasswordCustomValidationImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordCustomValidationTest {

    private Map<String, Error> errors;
    private ChangePasswordForm changePasswordForm;
    private User user;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private ChangePasswordCustomValidation changePasswordCustomValidation = new ChangePasswordCustomValidationImpl();

    @Before
    public void setUp() {

        changePasswordForm = new ChangePasswordForm();
        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");

        user = createUser();
    }

    @Test
    public void checkPasswordsMatchEqualsTest() {

        doReturn(true).when(bCryptPasswordEncoder).matches(changePasswordForm.getNewPassword(), user.getPassword());

        changePasswordForm.setConfirmationPassword("test124");

        errors = changePasswordCustomValidation.validate(changePasswordForm, user);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorConfirmationPassword"));
        assertTrue(errors.containsKey("error"));
    }

    @Test
    public void checkPasswordsMatchNullTest() {

        doReturn(true).when(bCryptPasswordEncoder).matches(changePasswordForm.getNewPassword(), user.getPassword());

        changePasswordForm.setConfirmationPassword(null);

        errors = changePasswordCustomValidation.validate(changePasswordForm, user);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorConfirmationPassword"));
        assertTrue(errors.containsKey("error"));
    }

    @Test
    public void checkCurrentPasswordsMatchTest() {

        doReturn(true).when(bCryptPasswordEncoder).matches(changePasswordForm.getNewPassword(), user.getPassword());

        errors = changePasswordCustomValidation.validate(changePasswordForm, user);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorCurrentPassword"));
        assertTrue(errors.containsKey("error"));
    }

    @Test
    public void checkCurrentPasswordsMatchNullTest() {

        changePasswordForm.setNewPassword(null);

        errors = changePasswordCustomValidation.validate(changePasswordForm, user);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorCurrentPassword"));
        assertTrue(errors.containsKey("error"));
    }

    private User createUser() {
        return UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("userTest")
                .withPassword("test123")
                .build();
    }
}
