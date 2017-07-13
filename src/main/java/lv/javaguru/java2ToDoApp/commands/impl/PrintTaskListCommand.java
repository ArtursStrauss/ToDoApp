package lv.javaguru.java2ToDoApp.commands.impl;

import lv.javaguru.java2ToDoApp.businesslogic.api.BusinessLogic;
import lv.javaguru.java2ToDoApp.commands.api.Command;
import lv.javaguru.java2ToDoApp.domain.Task;

public class PrintTaskListCommand implements Command {

    private BusinessLogic businessLogic;

    public PrintTaskListCommand(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }


    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print shopping list to console execution start!");
        for (Task task : businessLogic.getAllTasks()) {
            System.out.println(task.toString());
        }
        System.out.println("Print shopping list to console execution end!");
        System.out.println();
    }
}
