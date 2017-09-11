package lv.javaguru.java2ToDoApp.core.database.impl;

import lv.javaguru.java2ToDoApp.core.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Task save(Task task) throws DBException {

        session().save(task);
        return task;
    }

    @Override
    public Optional<Task> getById(Long id) throws DBException {

        Task task = (Task) session().get(Task.class, id);
        return Optional.ofNullable(task);
    }

    @Override
    public Optional<Task> getByTitle(String title) throws DBException {

        Task task = (Task) session().createCriteria(Task.class)
                .add(Restrictions.eq("title", title))
                .uniqueResult();
        return Optional.ofNullable(task);
    }

    @Override
    public List<Task> getAllByUserId(Long userId) throws DBException {

        return session()
                .createCriteria(Task.class)
                .add(Restrictions.eq("userId", userId))
                .list();
    }

    @Override
    public List<Task> getTaskListByUserAndTitle(User user, String title){
        return session()
                .createCriteria(Task.class)
                .add(Restrictions.eq("userId", user.getId()))
                .add(Restrictions.like("title","%" + title + "%"))
                .list();
    }

    @Override
    public List<Task> getAll() throws DBException {

        return session()
                .createCriteria(Task.class)
                .list();
    }

    @Override
    public void delete(Task task) throws DBException {

        session().delete(task);
    }

    @Override
    public void update(Task task) throws DBException {
        session().update(task);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
