package lv.javaguru.java2ToDoApp.core.validators.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordCustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
public class ChangePasswordCustomValidationImpl implements ChangePasswordCustomValidation {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ResourceBundle resourceBundle;

    public ChangePasswordCustomValidationImpl() {
        resourceBundle = ResourceBundle.getBundle("todoAppMessages");
    }

    public Map<String, Error> validate(ChangePasswordForm changePasswordForm, User user) {

        Map<String, Error> errors = Maps.newHashMap();

        checkPasswordsMatch(changePasswordForm).ifPresent(e -> errors.put("errorConfirmationPassword", e));
        checkCurrentPasswordsMatch(changePasswordForm, user).ifPresent(e -> errors.put("errorCurrentPassword", e));
        addGlobalRegistrationErrorAttribute(errors);

        return errors;
    }

    private Optional<Error> checkPasswordsMatch(ChangePasswordForm changePasswordForm) {

        if (changePasswordForm.getConfirmationPassword() == null ||
                !changePasswordForm.getConfirmationPassword().equals(changePasswordForm.getNewPassword())) {
            return Optional.of(new Error("confirmationPassword", resourceBundle.getString("account.password.confirmation.error")));
        }
        return Optional.empty();
    }

    private Optional<Error> checkCurrentPasswordsMatch(ChangePasswordForm changePasswordForm, User user) {
        if (changePasswordForm.getNewPassword() == null ||
                bCryptPasswordEncoder.matches(changePasswordForm.getNewPassword(), user.getPassword())) {
            return Optional.of(new Error("currentPassword", resourceBundle.getString("account.password.error")));
        }
        return Optional.empty();
    }

    private void addGlobalRegistrationErrorAttribute(Map<String, Error> errors) {
        if (!errors.isEmpty()) {
            errors.put("error", new Error("global", resourceBundle.getString("account.password.error.global")));
        }
    }
}
