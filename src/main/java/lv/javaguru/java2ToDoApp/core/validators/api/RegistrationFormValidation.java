package lv.javaguru.java2ToDoApp.core.validators.api;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;

import java.util.Map;

public interface RegistrationFormValidation {
    Map<String,Error> validate(RegistrationForm registrationForm);
}