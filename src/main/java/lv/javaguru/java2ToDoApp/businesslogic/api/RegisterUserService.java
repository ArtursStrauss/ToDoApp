package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;

public interface RegisterUserService {
    Response register(String login, String password, String fullName, String confirmPassword);
}
