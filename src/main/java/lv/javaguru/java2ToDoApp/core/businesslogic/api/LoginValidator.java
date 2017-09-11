package lv.javaguru.java2ToDoApp.core.businesslogic.api;

import lv.javaguru.java2ToDoApp.common.Error;

import java.util.Map;

public interface LoginValidator {
    Map<String,Error> validate(String login, String password);
}
