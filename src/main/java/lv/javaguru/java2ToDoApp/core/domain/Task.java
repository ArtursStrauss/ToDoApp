package lv.javaguru.java2ToDoApp.core.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Task extends BaseEntity {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "done", nullable = false)
    private Boolean done;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "priority", nullable = false, columnDefinition = "enum('DUMMY')")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Task() {
        this.priority = Priority.LOW;
    }

    public Task(Long id, String title, Boolean done, Date dueDate, Priority priority) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

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
        //System.out.println(done);
        this.done = done;
    }

    public void setDone(String done) {
        //System.out.println(done);
        Boolean parsedDone = Boolean.parseBoolean(done);
        this.done = parsedDone;
    }

    public Date getDueDate() {

        return dueDate;
    }

    public void setDueDate(Date dueDate) {

        this.dueDate = dueDate;
    }

    public void setDueDate(String dueDate) {

        DateFormat formatter = new SimpleDateFormat(this.DATE_FORMAT);
        Date parsedDate = new Date();
        try {
            parsedDate = formatter.parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.dueDate = parsedDate;
    }

    public Priority getPriority() {

        return priority;
    }

    public void setPriority(Priority priority) {

        this.priority = priority;
    }

    public void setPriority(String priority) {
        Priority parsedPriority = Priority.valueOf(priority);
        this.priority = parsedPriority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (userId != task.userId) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (done != null ? !done.equals(task.done) : task.done != null) return false;
        if (dueDate != null ? !dueDate.equals(task.dueDate) : task.dueDate != null) return false;
        return priority == task.priority;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
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
                ", userId=" + userId +
                '}';
    }
}
