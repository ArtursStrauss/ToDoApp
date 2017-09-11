package lv.javaguru.java2ToDoApp.core.database.api;

import lv.javaguru.java2ToDoApp.core.domain.User;

import java.util.Optional;

public interface UserDAO {

    User save(User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    void update(User user);

    void delete(User user);

}
