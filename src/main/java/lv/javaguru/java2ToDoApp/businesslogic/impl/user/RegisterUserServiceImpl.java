package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.RegisterUserService;
import lv.javaguru.java2ToDoApp.businesslogic.api.RegisterUserValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static lv.javaguru.java2ToDoApp.domain.UserBuilder.createUser;

@Component
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserValidator validator;
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public Response register(String login, String password, String fullName, String confirmPassword) {
        Map<String, Error> validationErrors = validator.validate(login, password, confirmPassword);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        User user = createUser()
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .build();

        userDAO.save(user);

        return Response.createSuccessResponse();
    }
}

