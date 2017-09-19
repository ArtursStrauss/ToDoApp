package lv.javaguru.java2ToDoApp.core.validators;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordCustomValidation;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import lv.javaguru.java2ToDoApp.core.validators.impl.ChangePasswordFormValidationImpl;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.domain.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordFormValidationTest {

    private User user;
    private ChangePasswordForm changePasswordForm;

    @Mock
    private FormStandardValidation formStandardValidation;
    @Mock
    private ChangePasswordCustomValidation changePasswordCustomValidation;

    @InjectMocks
    private ChangePasswordFormValidation changePasswordFormValidation = new ChangePasswordFormValidationImpl();

    @Before
    public void setUp() throws Exception {
        changePasswordForm = new ChangePasswordForm();

        user = createUser();
    }

    @Test
    public void standardValidateTest() {

        Map<String, Error> errors = new HashMap<String, Error>() {{
            put("test", new Error("test", "test"));
        }};

        doReturn(errors).when(formStandardValidation).validate(changePasswordForm);

        changePasswordFormValidation.validate(changePasswordForm, user);

        verify(formStandardValidation).validate(changePasswordForm);
        verify(changePasswordCustomValidation, never()).validate(changePasswordForm, user);
    }

    @Test
    public void customValidateTest() {

        doReturn(Maps.newHashMap()).when(formStandardValidation).validate(changePasswordForm);
        doReturn(Maps.newHashMap()).when(changePasswordCustomValidation).validate(changePasswordForm, user);

        changePasswordFormValidation.validate(changePasswordForm, user);

        verify(formStandardValidation).validate(changePasswordForm);
        verify(changePasswordCustomValidation).validate(changePasswordForm, user);
    }

    private User createUser() {
        return UserBuilder.createUser()
                .withId(new Long(1))
                .withLogin("userTest")
                .withPassword("test123")
                .build();
    }
}
