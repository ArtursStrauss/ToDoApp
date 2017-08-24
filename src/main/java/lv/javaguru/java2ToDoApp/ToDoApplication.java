package lv.javaguru.java2ToDoApp;

import lv.javaguru.java2ToDoApp.config.SpringAppConfig;
import lv.javaguru.java2ToDoApp.views.api.View;
import lv.javaguru.java2ToDoApp.views.impl.AddTaskView;
import lv.javaguru.java2ToDoApp.views.impl.EditTaskView;
import lv.javaguru.java2ToDoApp.views.impl.PrintTaskListView;
import lv.javaguru.java2ToDoApp.views.impl.RemoveTaskView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToDoApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        // Use cases:
        // 1. Add task to list
        // 2. Remove task from list
        // 3. Edit task from list
        // 4. Print task list to console
        // 5. Exit

        Map<Integer, View> commands = new HashMap<>();
        commands.put(1, applicationContext.getBean(AddTaskView.class));
        commands.put(2, applicationContext.getBean(RemoveTaskView.class));
        commands.put(3, applicationContext.getBean(EditTaskView.class));
        commands.put(4, applicationContext.getBean(PrintTaskListView.class));


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
