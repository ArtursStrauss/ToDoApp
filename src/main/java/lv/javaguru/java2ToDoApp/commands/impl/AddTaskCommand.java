package lv.javaguru.java2ToDoApp.commands.impl;


import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.commands.api.Command;

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
        System.out.print("Enter product title:");
        String title = sc.nextLine();
        System.out.print("Enter product description:");
        String description = sc.nextLine();

        ///////////////////////BL/////////////////////

        boolean result = businessLogic.addProduct(title, description);

        //////////////BL END//////////

        if (!result) {
            System.out.println("Can not addProduct this product. " +
                    "Already exist in the list");
        }

        System.out.println("Add product to list execution end!");
        System.out.println();
    }
}
