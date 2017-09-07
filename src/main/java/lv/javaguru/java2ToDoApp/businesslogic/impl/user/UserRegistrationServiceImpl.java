package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.RegistrationFormValidator;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import lv.javaguru.java2ToDoApp.domain.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Response register(RegistrationForm registrationForm) {

        Map<String, Error> validationErrors = registrationFormValidator.validate(registrationForm);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        User user = UserBuilder.createUser()
                .withFullName(registrationForm.getFullName())
                .withLogin(registrationForm.getLogin())
                .withPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()))
                .build();

        user = userDAO.save(user);

        return Response.createSuccessResponse(user);
    }
}

