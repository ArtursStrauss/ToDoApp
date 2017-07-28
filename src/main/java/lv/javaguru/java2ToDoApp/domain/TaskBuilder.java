package lv.javaguru.java2ToDoApp.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskBuilder {

    private static final String dateFormat = "yyyy-MM-dd";

    private Integer id;
    private String title;
    private Boolean done;
    private Date dueDate;
    private Priority priority;

    private TaskBuilder() {
    }

    public static TaskBuilder createTask() {

        return new TaskBuilder();
    }

    public static Task createTask(Integer id,
                                  String title,
                                  Boolean done,
                                  Date dueDate,
                                  Priority priority) {
        return createTask()
                .withId(id)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority).build();
    }

    public static Task createTask(Integer id,
                                  String title,
                                  String done,
                                  String dueDate,
                                  String priority) {
        return createTask()
                .withId(id)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority).build();
    }

    public Task build() {
        Task task = new Task();
        task.setId(this.id);
        task.setTitle(this.title);
        task.setDone(this.done);
        task.setDueDate(this.dueDate);
        task.setPriority(this.priority);
        return task;
    }

    public TaskBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TaskBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder withDone(Boolean done) {
        this.done = done;
        return this;
    }

    public TaskBuilder withDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder withDone(String done) {
        this.done = Boolean.parseBoolean(done);
        return this;
    }

    public TaskBuilder withDueDate(String dueDate) {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date parsedDate = new Date();
        try {
            parsedDate = formatter.parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.dueDate = parsedDate;
        return this;
    }

    public TaskBuilder withPriority(String priority) {
        this.priority = Priority.valueOf(priority);
        return this;
    }
}
