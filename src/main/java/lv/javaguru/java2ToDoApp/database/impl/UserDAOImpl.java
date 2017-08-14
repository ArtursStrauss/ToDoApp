package lv.javaguru.java2ToDoApp.database.impl;

import lv.javaguru.java2ToDoApp.database.api.UserDAO;
import lv.javaguru.java2ToDoApp.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        session().save(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        User user = (User) session().get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
