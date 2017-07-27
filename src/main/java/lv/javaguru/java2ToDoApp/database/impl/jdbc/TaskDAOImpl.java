package lv.javaguru.java2ToDoApp.database.impl.jdbc;

import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.database.impl.DBException;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2ToDoApp.domain.TaskBuilder.createTask;

public class TaskDAOImpl extends DAOImpl implements TaskDAO {

    @Override
    public Task save(Task task) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "insert into TASKS(id, title, done, due_date, priority) values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setBoolean(2, task.isDone());
            preparedStatement.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            preparedStatement.setString(4, task.getPriority().name());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.save()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return task;
    }

    @Override
    public Optional<Task> getById(Integer id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from TASKS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Task task = null;
            if (resultSet.next()) {
                task = createTask()
                        .withId(resultSet.getInt("id"))
                        .withTitle(resultSet.getString("title"))
                        .withDone(resultSet.getBoolean("done"))
                        .withDueDate(resultSet.getDate("due_date"))
                        .withPriority(resultSet.getString("priority"))
                        .build();
            }
            return Optional.ofNullable(task);
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Task> getAll() throws DBException {
        List<Task> tasks = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from TASKS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = createTask()
                        .withId(resultSet.getInt("id"))
                        .withTitle(resultSet.getString("title"))
                        .withDone(resultSet.getBoolean("done"))
                        .withDueDate(resultSet.getDate("due_date"))
                        .withPriority(resultSet.getString("priority"))
                        .build();
                tasks.add(task);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list ProductDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return tasks;
    }

    @Override
    public void delete(Task task) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from TASKS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, task.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Task task) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "update TASKS set title = ?, done = ?, due_date = ?, priority = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setBoolean(2, task.isDone());
            preparedStatement.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            preparedStatement.setString(4, task.getPriority().name());
            preparedStatement.setInt(5, task.getId());

            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TaskDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
