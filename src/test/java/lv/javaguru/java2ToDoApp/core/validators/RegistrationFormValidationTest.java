package lv.javaguru.java2ToDoApp.core.validators;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationCustomValidation;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationFormValidation;
import lv.javaguru.java2ToDoApp.core.validators.impl.RegistrationFormValidationImpl;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
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
public class RegistrationFormValidationTest {

    private RegistrationForm registrationForm;

    @Mock
    private FormStandardValidation formStandardValidation;
    @Mock
    private RegistrationCustomValidation registrationCustomValidation;

    @InjectMocks
    private RegistrationFormValidation registrationFormValidation = new RegistrationFormValidationImpl();

    @Before
    public void setUp() throws Exception {

        registrationForm = new RegistrationForm();
        registrationForm.setLogin("testUser");
        registrationForm.setFullName("Test User");
        registrationForm.setPassword("test456");
        registrationForm.setConfirmationPassword("test456");

    }

    @Test
    public void standardValidateTest() {

        Map<String, Error> errors = new HashMap<String, Error>() {{
            put("test", new Error("test", "test"));
        }};

        doReturn(errors).when(formStandardValidation).validate(registrationForm);

        registrationFormValidation.validate(registrationForm);

        verify(formStandardValidation).validate(registrationForm);
        verify(registrationCustomValidation, never()).validate(registrationForm);
    }

    @Test
    public void customValidateTest() {

        doReturn(Maps.newHashMap()).when(formStandardValidation).validate(registrationForm);
        doReturn(Maps.newHashMap()).when(registrationCustomValidation).validate(registrationForm);

        registrationFormValidation.validate(registrationForm);

        verify(formStandardValidation).validate(registrationForm);
        verify(registrationCustomValidation).validate(registrationForm);
    }
}
