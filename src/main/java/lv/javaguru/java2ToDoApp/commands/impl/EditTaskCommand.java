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

            if (!title.isEmpty()) {
                foundTask.get().setTitle(title);
            }

            System.out.print("Edit task status (true, false)  (if not, leave blank):");
            String stringBoolean = sc.nextLine();

            if (!stringBoolean.isEmpty()) {

                Boolean done = Boolean.parseBoolean(stringBoolean);
                foundTask.get().setDone(done);
            }

            System.out.println("Edit due date (format: YYYY-MM-DD) (if not, leave blank):");
            String dueDateString = sc.nextLine();

            if (!dueDateString.isEmpty()) {

                Date dueDate = new Date();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dueDate = formatter.parse(dueDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                foundTask.get().setDueDate(dueDate);
            }

            System.out.println("Edit priority (LOW, MEDIUM, HIGH) (if not, leave blank):");
            String priorityString = sc.nextLine();

            if (!dueDateString.isEmpty()) {
                Priority priority = Priority.valueOf(priorityString);
                foundTask.get().setPriority(priority);
            }

            ///////////////////////BL/////////////////////

            businessLogic.updateTask(foundTask.get());

            ///////////////////////BL END/////////////////

            System.out.println("Add task to list! Execution end!");
            System.out.println();
        } else {
            System.out.println("There is no task with such id: " + id);
        }
    }
}
