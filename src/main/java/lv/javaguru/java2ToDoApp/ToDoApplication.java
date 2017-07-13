package lv.javaguru.java2ToDoApp;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.businesslogic.impl.BusinessLogicImpl;
import lv.javaguru.java2ToDoApp.commands.api.Command;
import lv.javaguru.java2ToDoApp.commands.impl.AddTaskCommand;
import lv.javaguru.java2ToDoApp.commands.impl.PrintTaskListCommand;
import lv.javaguru.java2ToDoApp.commands.impl.RemoveTaskCommand;
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
        // 3. Print task list to console
        // 4. Exit

        TaskDatabase taskDatabase = new TaskDatabaseImpl();
        BusinessLogic businessLogic = new BusinessLogicImpl(taskDatabase);

        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, new AddTaskCommand(businessLogic));
        commands.put(2, new RemoveTaskCommand(businessLogic));
        commands.put(3, new PrintTaskListCommand(businessLogic));


        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 4) {
                break;
            } else {
                Command command = commands.get(menuItem);
                command.execute();
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println("ToDO Program Menu:");
        System.out.println("1. Add task to list");
        System.out.println("2. Remove task from list");
        System.out.println("3. Print task list to console");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
