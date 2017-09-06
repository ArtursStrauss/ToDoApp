package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User register(User user) {

        return userDAO.save(user);
    }
}

