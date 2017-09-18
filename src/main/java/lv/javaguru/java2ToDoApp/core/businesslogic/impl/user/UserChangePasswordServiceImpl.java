package lv.javaguru.java2ToDoApp.core.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.core.validators.api.ChangePasswordFormValidation;
import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserChangePasswordService;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Component
public class UserChangePasswordServiceImpl implements UserChangePasswordService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ChangePasswordFormValidation changePasswordFormValidation;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Response change(ChangePasswordForm changePasswordForm) {

        Optional<User> user = userDAO.getByLogin(changePasswordForm.getLogin());

        Map<String, Error> validationErrors = changePasswordFormValidation.validate(changePasswordForm, user.get());

        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        user.get().setPassword(bCryptPasswordEncoder.encode(changePasswordForm.getNewPassword()));

        userDAO.save(user.get());

        return Response.createSuccessResponse(user.get());
    }
}
