package lv.javaguru.java2ToDoApp.common.form;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(propertyViolationExists(violations, "login"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.login.required}"));
    }

    @Test
    public void testLoginRequiredEmptyString() {

        registrationForm.setLogin("");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "login"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.login.required}"));
    }

    @Test
    public void testFullNameRequired() {

        registrationForm.setFullName(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "fullName"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.fullName.required}"));
    }

    @Test
    public void testFullNameRequiredEmtyString() {

        registrationForm.setFullName("");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "fullName"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.fullName.required}"));
    }

    @Test
    public void testPasswordRequired() {

        registrationForm.setPassword(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "password"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.password.required}"));
    }

    @Test
    public void testPasswordRequiredEmptyString() {

        registrationForm.setPassword("");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "password"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.password.required}"));
    }

    @Test
    public void testConfirmationPasswordRequired() {

        registrationForm.setConfirmationPassword(null);

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "confirmationPassword"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.confirmationPassword.required}"));
    }

    @Test
    public void testPasswordSize() {

        registrationForm.setPassword("test1");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "password"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.password.size}"));
    }

    @Test
    public void testConfirmationPasswordSize() {

        registrationForm.setConfirmationPassword("test1");

        violations = validator.validate(registrationForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "confirmationPassword"));
        assertTrue(violationMessageTemplate(violations, "{registration.error.confirmationPassword.size}"));
    }

    private String getViolatedProperty(ConstraintViolation<RegistrationForm> violation) {
        return violation.getPropertyPath().toString();
    }

    private String getMessageTemplate(ConstraintViolation<RegistrationForm> violation) {
        return violation.getMessageTemplate();
    }

    private boolean propertyViolationExists(Set<ConstraintViolation<RegistrationForm>> violations, String property) {
        return violations.stream().anyMatch(e -> getViolatedProperty(e).equals(property));
    }

    private boolean violationMessageTemplate(Set<ConstraintViolation<RegistrationForm>> violations, String message) {
        return violations.stream().anyMatch(e -> getMessageTemplate(e).equals(message));
    }
}
