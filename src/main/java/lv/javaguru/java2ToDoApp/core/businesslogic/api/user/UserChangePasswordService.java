package lv.javaguru.java2ToDoApp.core.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;

public interface UserChangePasswordService {
    Response change(ChangePasswordForm changePasswordForm);
}
