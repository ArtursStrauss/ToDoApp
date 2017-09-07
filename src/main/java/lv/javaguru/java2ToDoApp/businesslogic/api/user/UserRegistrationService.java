package lv.javaguru.java2ToDoApp.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;

public interface UserRegistrationService {
    Response register(RegistrationForm registrationForm);
}
