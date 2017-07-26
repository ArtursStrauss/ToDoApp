package lv.javaguru.java2ToDoApp.views.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.views.api.View;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class EditTaskView implements View {

    private BusinessLogic businessLogic;

    public EditTaskView(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {

        System.out.println();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter task ID (integer):");
        Integer id = sc.nextInt();
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

        Response response = businessLogic.updateTask(id, title, stringBoolean, dueDateString, priorityString);

        ///////////////////////BL END/////////////////

        if (response.isFail()) {
            for (Error error : response.getErrors()) {
                System.out.println("Error: " + error.getField() + " " + error.getErrorMessage());
            }
            System.out.println("Can not updateTask this task!");
        }

        System.out.println("Update task in list! Execution end!");
        System.out.println();

        /**Optional<Task> foundTask = businessLogic.getTaskById(id);
         if (foundTask.isPresent()) {



         if (!title.isEmpty()) {
         foundTask.get().setTitle(title);
         }



         if (!stringBoolean.isEmpty()) {

         Boolean done = Boolean.parseBoolean(stringBoolean);
         foundTask.get().setDone(done);
         }



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
         }*/
    }
}
