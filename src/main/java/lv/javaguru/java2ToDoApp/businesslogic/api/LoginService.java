package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.common.Response;

public interface LoginService {
    Response login(String login, String password);
}
