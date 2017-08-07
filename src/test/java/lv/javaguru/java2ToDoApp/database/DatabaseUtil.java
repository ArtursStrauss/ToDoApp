package lv.javaguru.java2ToDoApp.database;

import lv.javaguru.java2ToDoApp.database.impl.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Component
public class DatabaseUtil {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseUtil(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void cleanDatabase() throws DBException {
        for (String tableName : getTableNames()) {
            jdbcTemplate.update("delete from " + tableName);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("tasks");
        return tableNames;
    }
}
