package lv.javaguru.java2ToDoApp.database.impl;

import com.google.common.collect.Lists;


import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Task> updateTask(Task task) {

        int index = this.getIndexByID(task.getId());
        this.tasks.set(index, task);

        return this.tasks.stream()
                .filter(p -> p.getId().equals(task.getId()))
                .findFirst();
    }

    @Override
    public List<Task> getAllTasks() {

        return Lists.newArrayList(this.tasks);
    }

    @Override
    public int getIndexByID(Long id) {

        return this.tasks.indexOf(this.getTaskById(id).get());
    }
}
