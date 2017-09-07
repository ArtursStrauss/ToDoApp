package lv.javaguru.java2ToDoApp.businesslogic.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.businesslogic.api.LoginValidator;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component()
@Scope("prototype")
public class LoginValidatorImpl implements LoginValidator {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Map<String, Error> validate(String login, String password) {

        Map<String, Error> errors = Maps.newHashMap();
        Optional<User> user = userDAO.getByLogin(login);

        validateLogin(user, password).ifPresent(e -> errors.put("LOGIN", e));
        return errors;
    }

    private Optional<Error> validateLogin(Optional<User> user, String password) {
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return Optional.empty();
            }
            return Optional.of(new Error("login", "Password is wrong!!!"));
        } else {
            return Optional.of(new Error("login", "Username is wrong!!!"));
        }
    }
}
