package lv.javaguru.java2ToDoApp.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.domain.User;

import java.util.Map;

public interface ChangePasswordFormValidation {
    Map<String,Error> validate(ChangePasswordForm changePasswordForm, User user);
}