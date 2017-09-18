package lv.javaguru.java2ToDoApp.core.validators.api;

import lv.javaguru.java2ToDoApp.common.Error;

import java.util.Map;

public interface FormStandardValidation {
    public <T> Map<String, Error> validate(T form);
}
