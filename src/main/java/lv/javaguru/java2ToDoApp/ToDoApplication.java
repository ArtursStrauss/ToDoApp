package lv.javaguru.java2ToDoApp;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.businesslogic.impl.AddTaskValidator;
import lv.javaguru.java2ToDoApp.businesslogic.impl.BusinessLogicImpl;
import lv.javaguru.java2ToDoApp.businesslogic.impl.UpdateTaskValidator;
import lv.javaguru.java2ToDoApp.views.api.View;
import lv.javaguru.java2ToDoApp.views.impl.AddTaskView;
import lv.javaguru.java2ToDoApp.views.impl.EditTaskView;
import lv.javaguru.java2ToDoApp.views.impl.PrintTaskListView;
import lv.javaguru.java2ToDoApp.views.impl.RemoveTaskView;
import lv.javaguru.java2ToDoApp.database.api.TaskDatabase;
import lv.javaguru.java2ToDoApp.database.impl.TaskDatabaseImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToDoApplication {
    public static void main(String[] args) {
        // Use cases:
        // 1. Add task to list
        // 2. Remove task from list
        // 3. Edit task from list
        // 4. Print task list to console
        // 5. Exit

        TaskDatabase taskDatabase = new TaskDatabaseImpl();
        AddTaskValidator addTaskValidator = new AddTaskValidator(taskDatabase);
        UpdateTaskValidator updateTaskValidator = new UpdateTaskValidator(taskDatabase);
        BusinessLogic businessLogic = new BusinessLogicImpl(taskDatabase, addTaskValidator, updateTaskValidator);

        Map<Integer, View> commands = new HashMap<>();
        commands.put(1, new AddTaskView(businessLogic));
        commands.put(2, new RemoveTaskView(businessLogic));
        commands.put(3, new EditTaskView(businessLogic));
        commands.put(4, new PrintTaskListView(businessLogic));


        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 5) {
                break;
            } else {
                View view = commands.get(menuItem);
                view.execute();
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println("ToDO Program Menu:");
        System.out.println("1. Add task to list");
        System.out.println("2. Remove task from list");
        System.out.println("3. Edit task from list");
        System.out.println("4. Print task list to console");
        System.out.println("5. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
