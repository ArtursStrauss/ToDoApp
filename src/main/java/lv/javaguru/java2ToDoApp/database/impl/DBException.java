package lv.javaguru.java2ToDoApp.database.impl;

public class DBException extends RuntimeException {
    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {

        super(message, cause);
    }

    public DBException(Throwable cause) {

        super(cause);
    }
}
