package lv.javaguru.java2ToDoApp.core.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.core.domain.User;

import java.util.Optional;

public interface UserGetService {
    Optional<User> getByLogin(String login);
}
