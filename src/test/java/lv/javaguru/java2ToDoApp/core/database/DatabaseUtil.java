package lv.javaguru.java2ToDoApp.core.database;

import lv.javaguru.java2ToDoApp.core.database.impl.DBException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DatabaseUtil {

    @Autowired
    SessionFactory sessionFactory;

    public void cleanDatabase() throws DBException {

        Session session = sessionFactory.getCurrentSession();
       // Transaction transaction = session.getTransaction();

        for (String tableName : getTableNames()) {

            SQLQuery query = session.createSQLQuery("delete from " + tableName);
            query.executeUpdate();

            //transaction.commit();
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("tasks");
        tableNames.add("app_users");
        return tableNames;
    }
}
