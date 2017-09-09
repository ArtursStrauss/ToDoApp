package lv.javaguru.java2ToDoApp.businesslogic.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.RegistrationFormValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.user.RegistrationFormValidatorImpl;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import lv.javaguru.java2ToDoApp.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationFormValidatorTest {

    private RegistrationForm registrationForm;
    private ResourceBundle resourceBundle;
    private User user;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private RegistrationFormValidator regValidator = new RegistrationFormValidatorImpl();

    @Before
    public void setUp() throws Exception {

        registrationForm = new RegistrationForm();
        resourceBundle = ResourceBundle.getBundle("ValidationMessages");
    }

    @Test
    public void loginRequiredTest() {

        //registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("errorLogin").getErrorMessage(), resourceBundle.getString("registration.error.login.required"));
    }

    @Test
    public void fullNameRequiredTest() {

        registrationForm.setLogin("testUser");
        //registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("errorFullName").getErrorMessage(), resourceBundle.getString("registration.error.fullName.required"));
    }

    @Test
    public void passwordRequiredTest() {

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        //registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("errorPassword").getErrorMessage(), resourceBundle.getString("registration.error.password.required"));
    }

    @Test
    public void confirmationPasswordRequiredTest() {

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        //registrationForm.setConfirmationPassword("test123");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("errorConfirmationPassword").getErrorMessage(), resourceBundle.getString("registration.error.confirmationPassword.required"));
    }

    /*
    not parsing - java.lang.IllegalArgumentException: can't parse argument number: min ??????????
    @Test
    public void confirmationPasswordSizeTest() {

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test1");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        Map<String, Integer> data = new HashMap<>();
        data.put("min", 6);

        //assertEquals(errors.get("errorConfirmationPassword").getErrorMessage(), resourceBundle.getString("registration.error.confirmationPassword.size"));
        System.out.println(errors.get("errorConfirmationPassword").getErrorMessage());
        System.out.println(MessageFormat.format(resourceBundle.getString("registration.error.confirmationPassword.size"), data));
        //assertEquals(errors.get("errorConfirmationPassword").getErrorMessage(), MessageFormat.format(resourceBundle.getString("registration.error.confirmationPassword.size"),data));
    }*/

    @Test
    public void checkPasswordsMatchTest() {

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test124");

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("errorConfirmationPasswordMatching").getErrorMessage(), resourceBundle.getString("register.error.password.confirmation.error"));
    }

    @Test
    public void alreadyExistTest() {

        user = UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("userTest")
                .withPassword("test123")
                .build();

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");

        doReturn(Optional.of(user)).when(userDAO).getByLogin(registrationForm.getLogin());

        Map<String, Error> errors = regValidator.validate(registrationForm);

        assertEquals(errors.get("error").getErrorMessage(), MessageFormat.format(resourceBundle.getString("register.error.global.account"), registrationForm.getLogin()));
    }

}
