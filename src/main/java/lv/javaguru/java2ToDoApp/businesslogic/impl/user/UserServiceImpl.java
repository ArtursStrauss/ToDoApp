package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.UserService;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public User getByLogin(String login) {
        return userDAO.getByLogin(login).get();
    }
}
