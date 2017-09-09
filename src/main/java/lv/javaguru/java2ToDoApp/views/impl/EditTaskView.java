package lv.javaguru.java2ToDoApp.views.impl;

import lv.javaguru.java2ToDoApp.views.api.View;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EditTaskView implements View {


    @Override
    public void execute() {

        System.out.println();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter task ID (integer):");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.print("Edit task title (if not, leave blank):");
        String title = sc.nextLine();

        System.out.print("Edit task status (true, false)  (if not, leave blank):");
        String stringBoolean = sc.nextLine();

        System.out.println("Edit due date (format: YYYY-MM-DD) (if not, leave blank):");
        String dueDateString = sc.nextLine();

        System.out.println("Edit priority (LOW, MEDIUM, HIGH) (if not, leave blank):");
        String priorityString = sc.nextLine();

        ///////////////////////BL/////////////////////

        //Response response = updateTaskService.updateTask(id, title, stringBoolean, dueDateString, priorityString);

        ///////////////////////BL END/////////////////

        //if (response.isFail()) {
        //    for (Error error : response.getErrors()) {
        //        System.out.println("Error: " + error.getField() + " " + error.getErrorMessage());
        //    }
        //    System.out.println("Can not updateTask this task!");
       // }

        System.out.println("Update task in list! Execution end!");
        System.out.println();
    }
}
