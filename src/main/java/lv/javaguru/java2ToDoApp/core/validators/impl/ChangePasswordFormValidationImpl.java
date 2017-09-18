package lv.javaguru.java2ToDoApp.core.validators.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordCustomValidation;
import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.domain.User;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class ChangePasswordFormValidationImpl implements ChangePasswordFormValidation {

    @Autowired
    private FormStandardValidation formStandardValidation;
    @Autowired
    private ChangePasswordCustomValidation changePasswordCustomValidation;

    @Override
    public Map<String, Error> validate(ChangePasswordForm changePasswordForm, User user) {

        Map<String, Error> errors = Maps.newHashMap();

        errors.putAll(formStandardValidation.validate(changePasswordForm));
        if (errors.isEmpty()) {
            errors.putAll(changePasswordCustomValidation.validate(changePasswordForm, user));
        }
        return errors;
    }
}
