package lv.javaguru.java2ToDoApp.common.util;

import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;

import java.util.List;

public class TaskListUtils {

    public static String getPriorityIcon(Priority priority) {

        String priorityIcon = "";

        switch (priority) {
            case HIGH:
                priorityIcon = "up";
                break;
            case MEDIUM:
                priorityIcon = "right";
                break;
            case LOW:
                priorityIcon = "down";
        }

        return priorityIcon;
    }

    public static String getStatusStyle(boolean status) {
        return status ? "badge-success" : "badge-secondary";
    }

    public static String getInputStyle(boolean status) {
        return status ? "is-invalid" : "";
    }

    public static String getStatusLabel(boolean status) {
        return status ? "DONE" : "TODO";
    }

    public static int countTotalDone(List<Task> todoList) {
        int count = 0;
        for (Task task : todoList) {
            if (task.isDone()) {
                count ++;
            }
        }
        return count;
    }
}
