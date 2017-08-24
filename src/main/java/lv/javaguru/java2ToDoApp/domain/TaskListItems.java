package lv.javaguru.java2ToDoApp.domain;

import javax.persistence.*;

@Entity
@Table(name="task_list_items")
public class TaskListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_list_id", nullable = false)
    private Long taskListId;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskListItems that = (TaskListItems) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taskListId != null ? !taskListId.equals(that.taskListId) : that.taskListId != null) return false;
        return taskId != null ? taskId.equals(that.taskId) : that.taskId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskListId != null ? taskListId.hashCode() : 0);
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        return result;
    }
}
