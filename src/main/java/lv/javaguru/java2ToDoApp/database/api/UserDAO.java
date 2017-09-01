package lv.javaguru.java2ToDoApp.database.api;

import lv.javaguru.java2ToDoApp.domain.User;

import java.util.Optional;

public interface UserDAO {

    User save(User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);


}
