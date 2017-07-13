package lv.javaguru.java2ToDoApp.domain;

import java.util.Date;

public class TaskBuilder {
    private Integer id;
    private String title;
    private boolean done;
    private Date dueDate;
    private Priority priority;

    private TaskBuilder() {
    }

    public static TaskBuilder createTask() {

        return new TaskBuilder();
    }

    public static Task createTask(Integer id,
                                  String title,
                                  boolean done,
                                  Date dueDate,
                                  Priority priority) {
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

    public TaskBuilder withDone(boolean done) {
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
}
