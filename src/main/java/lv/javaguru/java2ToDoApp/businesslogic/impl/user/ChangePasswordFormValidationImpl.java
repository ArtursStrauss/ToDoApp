package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;


@Component
@Scope("prototype")
public class ChangePasswordFormValidationImpl implements ChangePasswordFormValidation {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ResourceBundle resourceBundle;
    private Validator validator;


    public ChangePasswordFormValidationImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");
    }

    @Override
    public Map<String, Error> validate(ChangePasswordForm changePasswordForm, User user) {

        Map<String, Error> errors = Maps.newHashMap();

        validateCurrentPassword(changePasswordForm).ifPresent(e -> errors.put("errorCurrentPassword", e));
        validateNewPassword(changePasswordForm).ifPresent(e -> errors.put("errorNewPassword", e));
        validateConfirmationPassword(changePasswordForm).ifPresent(e -> errors.put("errorConfirmationPassword", e));

        checkPasswordsMatch(changePasswordForm).ifPresent(e -> errors.put("errorConfirmationPassword", e));
        checkCurrentPasswordsMatch(changePasswordForm, user).ifPresent(e -> errors.put("errorCurrentPassword", e));

        addGlobalRegistrationErrorAttribute(errors);

        return errors;
    }

    private void addGlobalRegistrationErrorAttribute(Map<String, Error> errors) {
        if (!errors.isEmpty()) {
            errors.put("error", new Error("global", resourceBundle.getString("account.password.error.global")));
        }
    }

    private Optional<Error> validateCurrentPassword(ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations = validator.validateProperty(changePasswordForm, "currentPassword");
        if (!constraintViolations.isEmpty()) {

            return Optional.of(new Error("currentPassword", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> validateNewPassword(ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations = validator.validateProperty(changePasswordForm, "newPassword");
        if (!constraintViolations.isEmpty()) {

            return Optional.of(new Error("newPassword", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> validateConfirmationPassword(ChangePasswordForm changePasswordForm) {
        Set<ConstraintViolation<ChangePasswordForm>> constraintViolations = validator.validateProperty(changePasswordForm, "confirmationPassword");
        if (!constraintViolations.isEmpty()) {

            return Optional.of(new Error("confirmationPassword", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> checkPasswordsMatch(ChangePasswordForm changePasswordForm) {
        if (!changePasswordForm.getConfirmationPassword().equals(changePasswordForm.getNewPassword())) {
            return Optional.of(new Error("confirmationPassword", resourceBundle.getString("account.password.confirmation.error")));
        }
        return Optional.empty();
    }

    private Optional<Error> checkCurrentPasswordsMatch(ChangePasswordForm changePasswordForm, User user) {
        if (bCryptPasswordEncoder.matches(changePasswordForm.getNewPassword(), user.getPassword())) {
            return Optional.of(new Error("currentPassword", resourceBundle.getString("account.password.error")));
        }
        return Optional.empty();
    }
}
