package lv.javaguru.java2ToDoApp.common.util;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.core.domain.Priority;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import org.junit.Test;

import java.util.List;

import static lv.javaguru.java2ToDoApp.common.util.TaskListUtils.*;
import static lv.javaguru.java2ToDoApp.core.domain.TaskBuilder.createTask;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaskListUtilsTest {

    @Test
    public void getPriorityIconTest() {

        String iconLow = getPriorityIcon(Priority.LOW);
        String iconMedium = getPriorityIcon(Priority.MEDIUM);
        String iconHigh = getPriorityIcon(Priority.HIGH);

        assertThat(iconLow, is("down"));
        assertThat(iconMedium, is("right"));
        assertThat(iconHigh, is("up"));
    }

    @Test
    public void getStatusStyleTest() {
        String badgeSuccess = getStatusStyle(true);
        String secondarySuccess = getStatusStyle(false);

        assertThat(badgeSuccess, is("badge-success"));
        assertThat(secondarySuccess, is("badge-secondary"));
    }

    @Test
    public void getInputStyleTest() {
        String inputStyle = getInputStyle(true);
        String inputStyle1False = getInputStyle(false);

        assertThat(inputStyle, is("is-invalid"));
        assertThat(inputStyle1False, is(""));
    }

    @Test
    public void getStatusLabelTest() {
        String statusLabel = getStatusLabel(true);
        String statusLabelFalse = getStatusLabel(false);

        assertThat(statusLabel, is("DONE"));
        assertThat(statusLabelFalse, is("TODO"));
    }

    @Test
    public void getSelectedTest() {
        String selectedTrue = getSelected(true);
        String selectedFalse = getSelected(false);

        assertThat(selectedTrue, is("selected"));
        assertThat(selectedFalse, is(""));
    }

    @Test
    public void countTotalDoneTest(){

        List<Task> tasks = Lists.newArrayList();

        Task task1 = create("Clean room", "false", "2017-09-09", "LOW");
        Task task2 = create("Clean second room", "true", "2017-09-10", "LOW");
        Task task3 = create("Clean third room", "true", "2017-09-10", "LOW");

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        int count = countTotalDone(tasks);

        assertThat(count, is(2));
    }

    private Task create(String title, String done, String dueDate, String priority) {

        Task task = createTask()
                .withId(null)
                .withTitle(title)
                .withDone(done)
                .withDueDate(dueDate)
                .withPriority(priority)
                .withUserId(new Long(1))
                .build();
        return task;
    }
}
