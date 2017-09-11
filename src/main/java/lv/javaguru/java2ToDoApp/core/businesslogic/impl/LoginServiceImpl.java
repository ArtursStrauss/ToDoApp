package lv.javaguru.java2ToDoApp.core.businesslogic.impl;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.LoginService;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginValidatorImpl validator;

    @Override
    @Transactional
    public Response login(String login, String password) {
        Map<String, Error> validationErrors = validator.validate(login, password);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        return Response.createSuccessResponse();
    }
}
