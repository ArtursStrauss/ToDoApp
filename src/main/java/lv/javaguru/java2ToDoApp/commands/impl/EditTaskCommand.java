package lv.javaguru.java2ToDoApp.commands.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.commands.api.Command;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class EditTaskCommand implements Command {

    private BusinessLogic businessLogic;

    public EditTaskCommand(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {

        System.out.println();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter task ID (integer):");
        Integer id = sc.nextInt();
        sc.nextLine();

        Optional<Task> foundTask = businessLogic.getTaskById(id);
        if (foundTask.isPresent()) {


            System.out.print("Edit task title (if not, leave blank):");
            String title = sc.nextLine();

            System.out.print("Edit task status (true, false)  (if not, leave blank):");
            String stringBoolean = sc.nextLine();

            Boolean done = Boolean.parseBoolean(stringBoolean);
            //Boolean done = sc.nextBoolean();

            System.out.println("Edit due date (format: YYYY-MM-DD) (if not, leave blank):");
            String dueDateString = sc.nextLine();

            Date dueDate = new Date();

            if (sc.hasNextLine()) {

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dueDate = formatter.parse(dueDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Edit priority (LOW, MEDIUM, HIGH) (if not, leave blank):");
            String priorityString = sc.nextLine();
            Priority priority = null;
            if (sc.hasNext()) {
                priority = Priority.valueOf(priorityString);
            }
            ///////////////////////BL/////////////////////

            Task task = new Task(id, title, done, dueDate, priority);
            businessLogic.updateTask(task);

            //////////////BL END//////////

            System.out.println("Add task to list! Execution end!");
            System.out.println();
        } else {
            System.out.println("There is no task with such id: " + id);
        }

    }
}
