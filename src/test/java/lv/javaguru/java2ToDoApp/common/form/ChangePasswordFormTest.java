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
        assertEquals(getViolatedProperty(violations), "currentPassword");
        assertEquals(getMessageTemplate(violations), "{update.currentPassword.required}");
    }

    @Test
    public void newPasswordRequiredTest() {

        changePasswordForm.setNewPassword(null);

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "newPassword");
        assertEquals(getMessageTemplate(violations), "{update.newPassword.required}");
    }

    @Test
    public void confirmationPasswordRequiredTest() {

        changePasswordForm.setConfirmationPassword(null);

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "confirmationPassword");
        assertEquals(getMessageTemplate(violations), "{update.confirmationPassword.required}");
    }

    @Test
    public void currentPasswordSizeTest() {

        changePasswordForm.setCurrentPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "currentPassword");
        assertEquals(getMessageTemplate(violations), "{update.currentPassword.size}");
    }

    @Test
    public void newPasswordSizeTest() {

        changePasswordForm.setNewPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "newPassword");
        assertEquals(getMessageTemplate(violations), "{update.newPassword.size}");
    }

    @Test
    public void confirmationPasswordSizeTest() {

        changePasswordForm.setConfirmationPassword("test1");

        violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
        assertEquals(getViolatedProperty(violations), "confirmationPassword");
        assertEquals(getMessageTemplate(violations), "{update.confirmationPassword.size}");
    }

    private String getViolatedProperty(Set<ConstraintViolation<ChangePasswordForm>> violations) {
        return violations.iterator().next().getPropertyPath().toString();
    }

    private String getMessageTemplate(Set<ConstraintViolation<ChangePasswordForm>> violations) {
        return violations.iterator().next().getMessageTemplate();
    }
}
