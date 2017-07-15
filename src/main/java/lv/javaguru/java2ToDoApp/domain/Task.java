package lv.javaguru.java2ToDoApp.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private Integer id;
    private String title;
    private Boolean done;
    private Date dueDate;
    private Priority priority;

    public Task() {
        this.priority = Priority.LOW;
    }

    public Task(Integer id, String title, Boolean done, Date dueDate, Priority priority) {
        this.id = id;
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

    public void setDone(Boolean done) {
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

    public boolean idNotNull() {
        return this.id != null;
    }

    public boolean titleNotNull() {
        return this.title != null;
    }

    public boolean doneNotNull() {
        return this.done != null;
    }

    public boolean dueDateNotNull() {
        return this.dueDate != null;
    }

    public boolean priorityNotNull() {
        return this.priority != null;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return "Task{" +
                "id=" + id + "." +
                ", title='" + title + '\'' +
                ", done=" + done +
                ", dueDate=" + dateFormat.format(dueDate) +
                ", priority=" + priority +
                '}';
    }
}
