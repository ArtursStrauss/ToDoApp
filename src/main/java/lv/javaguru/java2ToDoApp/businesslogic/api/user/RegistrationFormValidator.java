package lv.javaguru.java2ToDoApp.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;

import java.util.Map;

public interface RegistrationFormValidator {
    Map<String,Error> validate(RegistrationForm registrationForm);
}