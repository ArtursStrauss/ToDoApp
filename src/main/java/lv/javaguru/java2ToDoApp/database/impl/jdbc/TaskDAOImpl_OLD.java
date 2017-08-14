package lv.javaguru.java2ToDoApp.database.impl;

import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

//@Component
class TaskDAOImpl_OLD implements TaskDAO {

    private final String UPDATE_QUERY = "update tasks set title = :title, done = :done, due_date = :due_date, priority = :priority where id = :id";
    private final String DELETE_QUERY = "delete from tasks where id = ?";
    private final String ALL_QUERY = "SELECT * FROM tasks";
    private final String BY_ID_QUERY = "SELECT * FROM tasks WHERE id = ?";
    private final String BY_TITLE_QUERY = "SELECT * FROM tasks WHERE title = ?";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertTask;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TaskDAOImpl_OLD(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertTask = new SimpleJdbcInsert(dataSource)
                .withTableName("tasks")
                .usingColumns("title", "done", "due_date", "priority", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Task save(Task task) throws DBException {

        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());

        Map<String, Object> params = new HashMap<>();
        params.put("title", task.getTitle());
        params.put("done", task.isDone());
        params.put("due_date", task.getDueDate());
        params.put("priority", task.getPriority());
        params.put("created_at", task.getCreatedAt());
        params.put("updated_at", task.getUpdatedAt());

        Long newId = insertTask.executeAndReturnKey(params).longValue();
        task.setId(newId);

        return task;
    }

    @Override
    public Optional<Task> getById(Long id) throws DBException {

        //SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id",1);

        List<Task> tasks = jdbcTemplate.query(this.BY_ID_QUERY, new Object[]{id}, new TaskRowMapper());
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            if (tasks.size() == 1) {
                return Optional.ofNullable(tasks.get(0));
            } else {
                throw new IllegalStateException("More then one row returned!");
            }
        }
    }

    @Override
    public Optional<Task> getByTitle(String title) throws DBException {

        List<Task> tasks = jdbcTemplate.query(this.BY_TITLE_QUERY, new Object[]{title}, new TaskRowMapper());
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            if (tasks.size() == 1) {
                return Optional.ofNullable(tasks.get(0));
            } else {
                throw new IllegalStateException("More then one row returned!");
            }
        }
    }

    @Override
    public List<Task> getAll() throws DBException {

        //List<Task> tasks = jdbcTemplate.query(this.ALL_QUERY, new BeanPropertyRowMapper<Task>(Task.class));
        List<Task> tasks = jdbcTemplate.query(this.ALL_QUERY, new TaskRowMapper());

        return tasks;
    }

    @Override
    public void delete(Task task) throws DBException {

        jdbcTemplate.update(this.DELETE_QUERY, task.getId());
    }

    @Override
    public void update(Task task) throws DBException {
        System.out.println(task);
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", task.getId())
                .addValue("title", task.getTitle())
                .addValue("done", task.isDone())
                .addValue("due_date", task.getDueDate())
                .addValue("priority", task.getPriority().toString());

        namedParameterJdbcTemplate.update(this.UPDATE_QUERY, namedParameters);
    }
}
