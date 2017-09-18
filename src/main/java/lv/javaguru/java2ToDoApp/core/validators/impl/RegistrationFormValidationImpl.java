package lv.javaguru.java2ToDoApp.core.validators.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationFormValidation;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.core.validators.api.RegistrationCustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class RegistrationFormValidationImpl implements RegistrationFormValidation {

    @Autowired
    private FormStandardValidation formStandardValidation;
    @Autowired
    private RegistrationCustomValidation registrationCustomValidation;

    @Override
    public Map<String, Error> validate(RegistrationForm registrationForm) {

        Map<String, Error> errors = Maps.newHashMap();

        errors.putAll(formStandardValidation.validate(registrationForm));
        if (errors.isEmpty()) {
            errors.putAll(registrationCustomValidation.validate(registrationForm));
        }
        return errors;
    }
}