package lv.javaguru.java2ToDoApp.views.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.GetTasks;
import lv.javaguru.java2ToDoApp.views.api.View;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintTaskListView implements View {

    @Autowired
    private GetTasks getTasks;


    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print task list to console execution start!");
        for (Task task : getTasks.getAllTasks()) {
            System.out.println(task.toString());
        }
        System.out.println("Print task list to console execution end!");
        System.out.println();
    }
}
