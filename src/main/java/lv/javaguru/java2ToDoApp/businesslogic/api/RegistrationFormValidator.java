package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;

import java.util.Map;

public interface RegistrationFormValidator {
    Map<String,Error> validate(RegistrationForm registrationForm);
}