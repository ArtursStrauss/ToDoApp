package lv.javaguru.java2ToDoApp.commands.impl;


import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.commands.api.Command;
import lv.javaguru.java2ToDoApp.domain.Priority;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddTaskCommand implements Command {

    private BusinessLogic businessLogic;

    public AddTaskCommand(BusinessLogic businessLogic) {

        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {

        System.out.println();
        System.out.println("Add task to list execution start!");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter task ID (integer):");
        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter task:");
        String title = sc.nextLine();

        System.out.println("Enter due date (format: YYYY-MM-DD):");
        String dueDateString = sc.nextLine();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = new Date();
        try {
            dueDate = formatter.parse(dueDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter priority (LOW, MEDIUM, HIGH):");
        String priorityString = sc.nextLine();

        Priority priority = Priority.valueOf(priorityString);

        ///////////////////////BL/////////////////////
        businessLogic.addTask(id, title, false, dueDate, priority);

        //////////////BL END//////////

        System.out.println("Add task to list! Execution end!");
        System.out.println();
    }
}
