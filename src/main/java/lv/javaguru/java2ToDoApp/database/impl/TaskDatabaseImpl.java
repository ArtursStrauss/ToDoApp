package lv.javaguru.java2ToDoApp.database.impl;

import com.google.common.collect.Lists;

import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;
import java.util.Optional;

public class TaskDatabaseImpl implements TaskDatabase {

    private List<Task> tasks = Lists.newArrayList();

    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {

        this.tasks.remove(task);
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {
        return tasks.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateTask(Task task) {

        Optional<Task> foundTask = this.getTaskById(task.getId());

        if (!task.titleNotNull()) {
            task.setTitle(foundTask.get().getTitle());
        }

        if (!task.doneNotNull()) {
            task.setDone(foundTask.get().isDone());
        }

        if (!task.dueDateNotNull()) {
            task.setDueDate(foundTask.get().getDueDate());
        }

        if (!task.priorityNotNull()) {
            task.setPriority(foundTask.get().getPriority());
        }

        int index = this.tasks.indexOf(foundTask.get());
        this.tasks.set(index, task);
    }

    @Override
    public List<Task> getAllTasks() {

        return Lists.newArrayList(this.tasks);
    }
}
