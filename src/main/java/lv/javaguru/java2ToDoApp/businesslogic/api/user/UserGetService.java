package lv.javaguru.java2ToDoApp.businesslogic.api.user;

import lv.javaguru.java2ToDoApp.domain.User;

import java.util.Optional;

public interface UserGetService {
    Optional<User> getByLogin(String login);
}
