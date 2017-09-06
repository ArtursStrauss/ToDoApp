package lv.javaguru.java2ToDoApp.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserUpdateService;
import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserUpdateServiceImpl implements UserUpdateService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }
}
