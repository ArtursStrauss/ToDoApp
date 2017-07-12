package lv.javaguru.java2ToDoApp;

import java.util.Scanner;

public class ToDoApplication {
    public static void main(String[] args) {
        // Use cases:
        // 1. Add task to list
        // 2. Remove task from list
        // 3. Print task list to console
        // 4. Exit

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
