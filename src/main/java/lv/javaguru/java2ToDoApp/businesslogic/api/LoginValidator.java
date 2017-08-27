package lv.javaguru.java2ToDoApp.businesslogic.api;

import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;

import java.util.Map;

public interface LoginValidator {
    Map<String,Error> validate(String login, String password);
}
