package lv.javaguru.java2ToDoApp.core.domain;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TaskBuilderTest {

    private static final String dateFormat = "yyyy-MM-dd";

    @Test
    public void createTaskWithBuilderTest() {

        Task task = TaskBuilder.createTask()
                .withId(new Long(1))
                .withTitle("Clean room")
                .withDone("false")
                .withDueDate("2017-09-09")
                .withPriority("LOW")
                .withUserId(new Long(1))
                .build();

        assertThat(task.getId(), is(new Long(1)));
        assertThat(task.getTitle(), is("Clean room"));
        assertThat(task.isDone(), is(false));
        assertThat(formatDate(task.getDueDate()), is("2017-09-09"));
        assertThat(task.getPriority(), is(Priority.LOW));
        assertThat(task.getUserId(), is(new Long(1)));
    }

    private String formatDate(Date dueDate) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(dueDate);
    }
}
