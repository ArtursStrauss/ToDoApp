package lv.javaguru.java2ToDoApp.domain;

import java.util.Date;

public class Task {

    private Integer id;
    private String title;
    private boolean done;
    private Date dueDate;
    private Priority priority;

    public Task() {
        this.priority = Priority.LOW;
    }

    public Task(String title, boolean done, Priority priority, Date dueDate) {
        this.title = title;
        this.done = done;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (done != task.done) return false;
        if (!id.equals(task.id)) return false;
        if (!title.equals(task.title)) return false;
        if (!dueDate.equals(task.dueDate)) return false;
        return priority == task.priority;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + dueDate.hashCode();
        result = 31 * result + priority.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id + "." +
                ", title='" + title + '\'' +
                ", done=" + done +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                '}';
    }
}
