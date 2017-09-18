package lv.javaguru.java2ToDoApp.core.validators.api;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.domain.User;

import java.util.Map;

public interface ChangePasswordCustomValidation {
    public Map<String, Error> validate(ChangePasswordForm changePasswordForm, User user);
}
