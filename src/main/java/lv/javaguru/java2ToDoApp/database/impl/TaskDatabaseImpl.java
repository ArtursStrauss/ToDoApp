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
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {
        return tasks.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return Lists.newArrayList(tasks);
    }
}
