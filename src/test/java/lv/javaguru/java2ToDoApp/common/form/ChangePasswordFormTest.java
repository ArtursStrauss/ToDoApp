package lv.javaguru.java2ToDoApp.common.form;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordFormTest {

    private static Validator validator;
    private ChangePasswordForm changePasswordForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        changePasswordForm = new ChangePasswordForm();
    }

    @Test
    public void currentPasswordRequiredTest(){

        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void newPasswordRequiredTest(){

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void confirmationPasswordRequiredTest(){

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void currentPasswordSizeTest(){

        changePasswordForm.setCurrentPassword("test1");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void newPasswordSizeTest(){

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test1");
        changePasswordForm.setConfirmationPassword("test123");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void confirmationPasswordSizeTest(){

        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test1");
        Set<ConstraintViolation<ChangePasswordForm>> violations = validator.validate(changePasswordForm);

        assertFalse(violations.isEmpty());
    }
}
