package lv.javaguru.java2ToDoApp.views.impl;


import lv.javaguru.java2ToDoApp.businesslogic.api.AddTaskService;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.views.api.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class AddTaskView implements View {

    @Autowired
    private AddTaskService addTaskService;

    @Override
    public void execute() {

        System.out.println();
        System.out.println("Add task to list execution start!");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter task:");
        String title = sc.nextLine();

        System.out.println("Enter due date (format: YYYY-MM-DD):");
        String dueDate = sc.nextLine();

        System.out.println("Enter priority (LOW, MEDIUM, HIGH):");
        String priority = sc.nextLine();

        ///////////////////////BL/////////////////////

        Response response = addTaskService.addTask(title, "false", dueDate, priority);

        //////////////BL END//////////

        //if (response.isFail()) {
        //    for (Error error : response.getErrors()) {
        //        System.out.println("Error: " + error.getField() + " " + error.getErrorMessage());
        //    }
        //    System.out.println("Can not addTask this task!");
       // }

        System.out.println("Add task to list! Execution end!");
        System.out.println();
    }
}
