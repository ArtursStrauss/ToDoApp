package lv.javaguru.java2ToDoApp.core.validators;


import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.config.SpringAppConfig;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class FormStandardValidationTest {

    @Autowired
    private FormStandardValidation formStandardValidation;

    private ChangePasswordForm changePasswordForm;

    private Map<String, Error> errors;

    @Before
    public void setUp() {

        changePasswordForm = new ChangePasswordForm();
        changePasswordForm.setCurrentPassword("test123");
        changePasswordForm.setNewPassword("test123");
        changePasswordForm.setConfirmationPassword("test123");

        errors = Maps.newHashMap();
    }

    @Test
    public void ErrorMessagesReturned() {

        changePasswordForm.setCurrentPassword(null);
        changePasswordForm.setNewPassword(null);
        changePasswordForm.setConfirmationPassword(null);

        errors = formStandardValidation.validate(changePasswordForm);

        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("errorCurrentPassword"));
        assertTrue(errors.containsKey("errorNewPassword"));
        assertTrue(errors.containsKey("errorConfirmationPassword"));
    }

    @Test
    public void ErrorMessagesEmpty() {

        errors = formStandardValidation.validate(changePasswordForm);

        assertTrue(errors.isEmpty());
    }
}
