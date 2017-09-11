package lv.javaguru.java2ToDoApp.core.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserGetService;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserGetServiceImpl implements UserGetService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByLogin(String login) {
        return userDAO.getByLogin(login);
    }
}
