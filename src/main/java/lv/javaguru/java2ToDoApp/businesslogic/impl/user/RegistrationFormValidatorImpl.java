package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.RegistrationFormValidator;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@Scope("prototype")
public class RegistrationFormValidatorImpl implements RegistrationFormValidator {

    @Autowired
    private UserDAO userDAO;

    private ResourceBundle resourceBundle;
    private Validator validator;

    public RegistrationFormValidatorImpl() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");
    }

    @Override
    public Map<String, Error> validate(RegistrationForm registrationForm) {
        Map<String, Error> errors = Maps.newHashMap();

        validateLogin(registrationForm).ifPresent(e -> errors.put("errorLogin", e));
        validateFullName(registrationForm).ifPresent(e -> errors.put("errorFullName", e));
        validatePassword(registrationForm).ifPresent(e -> errors.put("errorPassword", e));
        validateConfirmationPassword(registrationForm).ifPresent(e -> errors.put("errorConfirmationPassword", e));
        checkPasswordsMatch(registrationForm).ifPresent(e -> errors.put("errorConfirmationPasswordMatching", e));

        addGlobalRegistrationErrorAttribute(errors);

        alreadyExist(registrationForm, errors);

        return errors;
    }

    private void addGlobalRegistrationErrorAttribute(Map<String, Error> errors) {
        if (!errors.isEmpty()) {
            errors.put("error", new Error("global", resourceBundle.getString("register.error.global")));
        }
    }

    private Optional<Error> validateLogin(RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "login");
        if (!constraintViolations.isEmpty()) {
            return Optional.of(new Error("login", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> validateFullName(RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "fullName");
        if (!constraintViolations.isEmpty()) {
            return Optional.of(new Error("fullName", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> validatePassword(RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "password");
        if (!constraintViolations.isEmpty()) {
            return Optional.of(new Error("password", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> validateConfirmationPassword(RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "confirmationPassword");
        if (!constraintViolations.isEmpty()) {
            return Optional.of(new Error("confirmationPassword", constraintViolations.iterator().next().getMessage()));
        }
        return Optional.empty();
    }

    private Optional<Error> checkPasswordsMatch(RegistrationForm registrationForm) {

        if(!validateConfirmationPassword(registrationForm).isPresent()) {
            if (!registrationForm.getConfirmationPassword().equals(registrationForm.getPassword())) {
                return Optional.of(new Error("confirmationPassword", resourceBundle.getString("register.error.password.confirmation.error")));
            }
        }
        return Optional.empty();
    }

    private void alreadyExist(RegistrationForm registrationForm, Map<String, Error> errors) {
        if (userDAO.getByLogin(registrationForm.getLogin()).isPresent()) {
            errors.put("error", new Error("login", MessageFormat.format(resourceBundle.getString("register.error.global.account"), registrationForm.getLogin())));
        }
    }
}