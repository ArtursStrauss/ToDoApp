package lv.javaguru.java2ToDoApp.common.form;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationFormTest {

    private static Validator validator;
    private RegistrationForm registrationForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        registrationForm = new RegistrationForm();
    }

    @Test
    public void testLoginRequired(){
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testFullNameRequired(){
        registrationForm.setLogin("testUser");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testPasswordRequired(){
        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testConfirmationPasswordRequired(){
        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testPasswordSize(){
        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test1");
        registrationForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testConfirmationPasswordSize(){
        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test1");
        Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
    }
}
