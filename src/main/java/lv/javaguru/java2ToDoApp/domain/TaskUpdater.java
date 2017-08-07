package lv.javaguru.java2ToDoApp.domain;

import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskUpdater {

    private Task task;
    @Autowired
    private TaskDAO taskDAO;

    public TaskUpdater getTask(Integer id) {
        this.task = taskDAO.getById(id).get();
        return this;
    }

    public TaskUpdater updateTitle(String title) {
        if (!title.isEmpty()) {
            this.task.setTitle(title);
        }
        return this;
    }

    public TaskUpdater updateDone(String done) {
        if (!done.isEmpty()) {
            task.setDone(done);
        }
        return this;
    }

    public TaskUpdater updateDueDate(String dueDate) {
        if (!dueDate.isEmpty()) {
            task.setDueDate(dueDate);
        }
        return this;
    }

    public TaskUpdater updatePriority(String priority) {
        if (!priority.isEmpty()) {
            task.setPriority(priority);
        }
        return this;
    }

    public Task update() {

        return this.task;
    }
}
