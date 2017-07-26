package lv.javaguru.java2ToDoApp.views.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.views.api.View;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.Optional;
import java.util.Scanner;

public class RemoveTaskView implements View {

    private BusinessLogic businessLogic;

    public RemoveTaskView(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {

        System.out.println();
        System.out.println("Remove task from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product id:");
        final Integer id = sc.nextInt();

        ///////////////////BL/////////////////////////
        Optional<Task> task = businessLogic.getTaskById(id);

        boolean result = businessLogic.removeTask(task.get());

        ////////////////////BL end /////////////////

        if (result) {
            System.out.println("Product " + task.get().toString() + " was found and will be removed from list!");
        } else {
            System.out.println("Product with ID " + id + " not found and not be removed from list!");
        }

        System.out.println("Remove product from list execution end!");
        System.out.println();

    }
}
