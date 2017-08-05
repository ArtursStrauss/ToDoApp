package lv.javaguru.java2ToDoApp.database.impl.jdbc;

import lv.javaguru.java2ToDoApp.database.impl.DBException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOImpl {

    //private static final String DB_CONFIG_FILE = "database.properties";

    @Value("${jdbc.url}")
    protected String jdbcUrl;

    @Value("${driverClass}")
    protected String driverClass;

    @Value("${database.user.name}")
    protected String userName;

    @Value("${database.user.password}")
    protected String password;

    public DAOImpl() {
        //initDatabaseConnectionProperties();
        //registerJDBCDriver();
    }

    private void registerJDBCDriver() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            System.out.println("Exception while registering JDBC driver!");
            e.printStackTrace();
        }
    }

    /**private void initDatabaseConnectionProperties() {
        Properties properties = new Properties();
        try {

            properties.load(DAOImpl.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));

            jdbcUrl = properties.getProperty("jdbcUrl");
            driverClass = properties.getProperty("driverClass");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");

        } catch (IOException e) {
            System.out.println("Exception while reading JDBC configuration from file = " + DB_CONFIG_FILE);
            e.printStackTrace();
        }
    }*/

    protected Connection getConnection() throws DBException {
        try {
            return DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    protected void closeConnection(Connection connection) throws DBException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }
}
