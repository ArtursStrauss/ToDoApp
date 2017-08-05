package lv.javaguru.java2ToDoApp.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        task.setDone(rs.getBoolean("done"));
        task.setDueDate(rs.getDate("due_date"));
        task.setPriority(rs.getString("priority"));

        return task;
    }
}
