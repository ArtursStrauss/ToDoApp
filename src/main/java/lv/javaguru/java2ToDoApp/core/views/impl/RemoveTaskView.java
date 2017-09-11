package lv.javaguru.java2ToDoApp.core.views.impl;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.core.views.api.View;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class RemoveTaskView implements View {

    @Autowired
    private TaskGetService taskGetService;

    @Override
    public void execute() {

        System.out.println();
        System.out.println("Remove task from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter task id:");
        final Long id = sc.nextLong();

        ///////////////////BL/////////////////////////
        Optional<Task> task = taskGetService.getTaskById(id);

        //boolean result = removeTaskService.removeTask(task.get());

        ////////////////////BL end /////////////////

        //if (result) {
        //    System.out.println("Task " + task.get().toString() + " was found and will be removed from list!");
        //} else {
        //    System.out.println("Task with ID " + id + " not found and not be removed from list!");
        //}

        System.out.println("Remove task from list execution end!");
        System.out.println();

    }
}
