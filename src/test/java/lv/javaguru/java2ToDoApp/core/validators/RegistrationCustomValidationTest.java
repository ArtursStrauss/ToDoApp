package lv.javaguru.java2ToDoApp.core.validators;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationCustomValidation;
import lv.javaguru.java2ToDoApp.core.validators.impl.RegistrationCustomValidationImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationCustomValidationTest {

    private Map<String, Error> errors;
    private RegistrationForm registrationForm;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private RegistrationCustomValidation registrationCustomValidation = new RegistrationCustomValidationImpl();


    @Before
    public void setUp() throws Exception {

        registrationForm = new RegistrationForm();
        registrationForm.setLogin("testUser2");
        registrationForm.setFullName("Test User2");
        registrationForm.setPassword("test456");
        registrationForm.setConfirmationPassword("test456");
    }

    @Test
    public void checkPasswordsMatchTest() {

        registrationForm.setConfirmationPassword("test457");

        errors = registrationCustomValidation.validate(registrationForm);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorConfirmationPasswordMatching"));
        assertTrue(errors.containsKey("error"));
    }

    @Test
    public void alreadyExistTest() {

        doReturn(createUser()).when(userDAO).getByLogin(registrationForm.getLogin());

        errors = registrationCustomValidation.validate(registrationForm);

        assertFalse(errors.isEmpty());
        assertEquals("login", errors.get("error").getField());
        assertTrue(errors.containsKey("error"));
    }

    private Optional<User> createUser() {
        return Optional.of(UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("testUser2")
                .withPassword("test456")
                .build());
    }
}
