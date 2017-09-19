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

public class ChangePasswordFormTest {

    private Validator validator;
    private Set<ConstraintViolation<ChangePasswordForm>> violations;
    private ChangePasswordForm changePasswordForm;

    @Before
    public void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        changePasswordForm = new ChangePasswordForm();
        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");
    }

    @Test
    public void currentPasswordRequiredTest() {

        changePasswordForm.setCurrentPassword(null);

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "currentPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.currentPassword.required}"));
    }

    @Test
    public void newPasswordRequiredTest() {

        changePasswordForm.setNewPassword(null);

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "newPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.newPassword.required}"));
    }

    @Test
    public void confirmationPasswordRequiredTest() {

        changePasswordForm.setConfirmationPassword(null);

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "confirmationPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.confirmationPassword.required}"));
    }

    @Test
    public void currentPasswordSizeTest() {

        changePasswordForm.setCurrentPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "currentPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.currentPassword.size}"));
    }

    @Test
    public void newPasswordSizeTest() {

        changePasswordForm.setNewPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "newPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.newPassword.size}"));
    }

    @Test
    public void confirmationPasswordSizeTest() {

        changePasswordForm.setConfirmationPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertTrue(propertyViolationExists(violations, "confirmationPassword"));
        assertTrue(violationMessageTemplate(violations, "{update.confirmationPassword.size}"));
    }

    private String getViolatedProperty(ConstraintViolation<ChangePasswordForm> violation) {
        return violation.getPropertyPath().toString();
    }

    private String getMessageTemplate(ConstraintViolation<ChangePasswordForm> violation) {
        return violation.getMessageTemplate();
    }

    private boolean propertyViolationExists(Set<ConstraintViolation<ChangePasswordForm>> violations, String property) {
        return violations.stream().anyMatch(e -> getViolatedProperty(e).equals(property));
    }

    private boolean violationMessageTemplate(Set<ConstraintViolation<ChangePasswordForm>> violations, String message) {
        return violations.stream().anyMatch(e -> getMessageTemplate(e).equals(message));
    }
}
