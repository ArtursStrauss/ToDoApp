package lv.javaguru.java2ToDoApp.core.validators.impl;

import com.google.common.collect.Maps;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.core.validators.api.FormStandardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@Scope("prototype")
public class FormStandardValidationImpl implements FormStandardValidation {

    @Autowired
    private Validator validator;
    @Autowired
    private ResourceBundle fieldErrorCodeMapping;

    public <T> Map<String, Error> validate(T form) {

        Map<String, Error> errors = Maps.newHashMap();

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(form);

        constraintViolations.forEach(e -> populatingError(e, errors));

        return errors;
    }

    private void populatingError(ConstraintViolation violation, Map<String, Error> errors) {
        errors.put(getViolationErrorCode(violation), createError(violation));
    }

    private Error createError(ConstraintViolation violation) {
        return new Error(getViolatedProperty(violation), violation.getMessage());
    }

    private String getViolatedProperty(ConstraintViolation violation) {
        return violation.getPropertyPath().toString();
    }

    private String getViolationErrorCode(ConstraintViolation violation) {
        return fieldErrorCodeMapping.getString(getViolatedProperty(violation));
    }
}
