package lv.javaguru.java2ToDoApp.core.validators.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationCustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
public class RegistrationCustomValidationImpl implements RegistrationCustomValidation {

    @Autowired
    private UserDAO userDAO;

    private ResourceBundle resourceBundle;

    public RegistrationCustomValidationImpl() {
        resourceBundle = ResourceBundle.getBundle("todoAppMessages");
    }

    @Override
    public Map<String, Error> validate(RegistrationForm registrationForm) {

        Map<String, Error> errors = Maps.newHashMap();

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

    private Optional<Error> checkPasswordsMatch(RegistrationForm registrationForm) {

        if (registrationForm.getConfirmationPassword() != null) {
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
