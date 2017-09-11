package lv.javaguru.java2ToDoApp.core.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;

public interface UserRegistrationService {
    Response register(RegistrationForm registrationForm);
}
