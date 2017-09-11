package lv.javaguru.java2ToDoApp.core.businesslogic.impl.user;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserDeleteService;
import lv.javaguru.java2ToDoApp.core.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDeleteServiceImpl implements UserDeleteService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void delete(User user){

        userDAO.delete(user);
    }
}
