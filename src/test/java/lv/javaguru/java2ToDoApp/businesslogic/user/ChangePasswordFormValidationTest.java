package lv.javaguru.java2ToDoApp.businesslogic.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.businesslogic.impl.user.ChangePasswordFormValidationImpl;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.domain.User;
import lv.javaguru.java2ToDoApp.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordFormValidationTest {

    private User user;
    private ResourceBundle resourceBundle;
    private ChangePasswordForm changePasswordForm;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private ChangePasswordFormValidation changePasswordFormValidation = new ChangePasswordFormValidationImpl();

    @Before
    public void setUp() throws Exception {
        changePasswordForm = new ChangePasswordForm();
        resourceBundle = ResourceBundle.getBundle("ValidationMessages");

        user = UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("userTest")
                .withPassword("test123")
                .build();
    }

    @Test
    public void currentPasswordRequiredTest() {

        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");

        Map<String, Error> errors = changePasswordFormValidation.validate(changePasswordForm, user);

        assertEquals(errors.get("errorCurrentPassword").getErrorMessage(), resourceBundle.getString("update.currentPassword.required"));
    }

    @Test
    public void newPasswordRequiredTest() {

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");

        Map<String, Error> errors = changePasswordFormValidation.validate(changePasswordForm, user);

        assertEquals(errors.get("errorNewPassword").getErrorMessage(), resourceBundle.getString("update.newPassword.required"));
    }

    @Test
    public void confirmationPasswordRequiredTest() {

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");

        Map<String, Error> errors = changePasswordFormValidation.validate(changePasswordForm, user);

        assertEquals(errors.get("errorConfirmationPassword").getErrorMessage(), resourceBundle.getString("update.confirmationPassword.required"));
    }

    @Test
    public void checkPasswordsMatchTest() {

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test124");
        changePasswordForm.setConfirmationPassword("test125");

        Map<String, Error> errors = changePasswordFormValidation.validate(changePasswordForm, user);

        assertEquals(errors.get("errorConfirmationPassword").getErrorMessage(), resourceBundle.getString("account.password.confirmation.error"));
    }

    @Test
    public void checkCurrentPasswordsMatchTest() {

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");

        doReturn(true).when(bCryptPasswordEncoder).matches(changePasswordForm.getNewPassword(), user.getPassword());

        Map<String, Error> errors = changePasswordFormValidation.validate(changePasswordForm, user);

        assertEquals(errors.get("errorCurrentPassword").getErrorMessage(), resourceBundle.getString("account.password.error"));
        verify(bCryptPasswordEncoder).matches(changePasswordForm.getNewPassword(), user.getPassword());
    }
}
