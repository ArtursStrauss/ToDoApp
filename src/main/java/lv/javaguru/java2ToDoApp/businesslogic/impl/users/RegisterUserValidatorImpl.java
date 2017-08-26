package lv.javaguru.java2ToDoApp.businesslogic.impl.users;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.RegisterUserValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class RegisterUserValidatorImpl implements RegisterUserValidator {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Map<String, Error> validate(String login, String password, String confirmPassword) {
        //List<Error> errors = Lists.newArrayList();
        Map<String, Error> errors = Maps.newHashMap();
        validateLogin(login).ifPresent(e -> errors.put("LOGIN", e));
        validatePassword(password).ifPresent(e -> errors.put("PASSWORD", e));
        validateConfirmPassword(password, confirmPassword).ifPresent(e -> errors.put("CONFIRM_PASSWORD", e));
        return errors;
    }

    private Optional<Error> validateLogin(String login) {
        if (login == null || "".equals(login)) {
            return Optional.of(new Error("login", "Must be not empty"));
        } else if (alreadyExist(login)) {
            return Optional.of(new Error("login", "Already exist"));
        } else {
            return Optional.empty();
        }
    }

    private boolean alreadyExist(String login) {

        return userDAO.getByLogin(login).isPresent();
    }

    private Optional<Error> validatePassword(String password) {
        if (password == null || "".equals(password)) {
            return Optional.of(new Error("password", "Must be not empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword == null || "".equals(confirmPassword)) {
            return Optional.of(new Error("confirmPassword", "Must be not empty"));
        } else if (!password.equals(confirmPassword)) {
            return Optional.of(new Error("confirmPassword", "Must be equals to password"));
        } else {
            return Optional.empty();
        }
    }
}