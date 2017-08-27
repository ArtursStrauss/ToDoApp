package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.domain.User;

public interface UserService {
    User getByLogin(String login);
}
