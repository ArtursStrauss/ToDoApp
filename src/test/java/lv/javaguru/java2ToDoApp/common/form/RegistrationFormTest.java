package lv.javaguru.java2ToDoApp.common.form;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RegistrationFormTest {

    private Validator validator;
    private Set<ConstraintViolation<RegistrationForm>> violations;
    private RegistrationForm registrationForm;

    @Before
    public void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        registrationForm = new RegistrationForm();

        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test123");
        registrationForm.setConfirmationPassword("test123");
    }

    @Test
    public void testLoginRequired() {

        registrationForm.setLogin(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "login");
        assertEquals(getMessageTemplate(violations), "{registration.error.login.required}");
    }

    @Test
    public void testLoginRequiredEmptyString() {

        registrationForm.setLogin("");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "login");
        assertEquals(getMessageTemplate(violations), "{registration.error.login.required}");
    }

    @Test
    public void testFullNameRequired() {

        registrationForm.setFullName(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "fullName");
        assertEquals(getMessageTemplate(violations), "{registration.error.fullName.required}");
    }

    @Test
    public void testFullNameRequiredEmtyString() {

        registrationForm.setFullName("");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "fullName");
        assertEquals(getMessageTemplate(violations), "{registration.error.fullName.required}");
    }

    @Test
    public void testPasswordRequired() {

        registrationForm.setPassword(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "password");
        assertEquals(getMessageTemplate(violations), "{registration.error.password.required}");
    }

    @Test
    public void testPasswordRequiredEmptyString() {

        registrationForm.setPassword("");

        violations = validator.validate(registrationForm);
        //System.out.println(registrationForm);
        //System.out.println(violations);
        assertFalse(violations.isEmpty());
        assertEquals("password", getViolatedProperty(violations));
        assertEquals("{registration.error.password.required}", getMessageTemplate(violations));
    }

    @Test
    public void testConfirmationPasswordRequired() {

        registrationForm.setConfirmationPassword(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "confirmationPassword");
        assertEquals(getMessageTemplate(violations), "{registration.error.confirmationPassword.required}");
    }

    @Test
    public void testPasswordSize() {

        registrationForm.setPassword("test1");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "password");
        assertEquals(getMessageTemplate(violations), "{registration.error.password.size}");
    }

    @Test
    public void testConfirmationPasswordSize() {

        registrationForm.setConfirmationPassword("test1");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "confirmationPassword");
        assertEquals(getMessageTemplate(violations), "{registration.error.confirmationPassword.size}");
    }

    private String getViolatedProperty(Set<ConstraintViolation<RegistrationForm>> violations) {
        return violations.iterator().next().getPropertyPath().toString();
    }

    private String getMessageTemplate(Set<ConstraintViolation<RegistrationForm>> violations) {
        return violations.iterator().next().getMessageTemplate();
    }
}
