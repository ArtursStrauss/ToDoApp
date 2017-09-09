package lv.javaguru.java2ToDoApp.businesslogic.task;

import lv.javaguru.java2ToDoApp.businesslogic.impl.task.AddTaskValidator;
import lv.javaguru.java2ToDoApp.common.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddTaskValidatorTest {

    private AddTaskValidator validator = new AddTaskValidator();

    @Test
    public void shouldReturnErrorWhenTitleIsNull() {

        List<Error> errors = validator.validate(null, "false", "2017-09-09", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("title"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {

        List<Error> errors = validator.validate("", "false", "2017-09-09", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("title"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenDoneIsNull() {

        List<Error> errors = validator.validate("buy desa", null, "2017-09-09", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("done"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenDoneIsEmpty() {

        List<Error> errors = validator.validate("buy desa", "", "2017-09-09", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("done"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenDoneIsNotBooleanValue() {

        List<Error> errors = validator.validate("buy desa", "aaa", "2017-09-09", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("done"));
        assertThat(errors.get(0).getErrorMessage(), is("This is not a boolean value!"));
    }

    @Test
    public void shouldReturnErrorWhenDueDateIsNull() {

        List<Error> errors = validator.validate("buy desa", "false", null, "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("dueDate"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenDueDateIsEmpty() {

        List<Error> errors = validator.validate("buy desa", "false", "", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("dueDate"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenDueDateIsNotValidFormat() {

        List<Error> errors = validator.validate("buy desa", "false", "09-09-2017", "LOW");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("dueDate"));
        assertThat(errors.get(0).getErrorMessage(), is("This is not valid date format (yyyy-MM-dd)!"));
    }

    @Test
    public void shouldReturnErrorWhenPriorityIsNull() {

        List<Error> errors = validator.validate("buy desa", "false", "2017-09-09", null);
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("priority"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenPriorityIsEmpty() {

        List<Error> errors = validator.validate("buy desa", "false", "2017-09-09", "");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("priority"));
        assertThat(errors.get(0).getErrorMessage(), is("Must not be empty!"));
    }

    @Test
    public void shouldReturnErrorWhenPriorityIsNotValidFormat() {

        List<Error> errors = validator.validate("buy desa", "false", "2017-09-09", "AAA");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("priority"));
        assertThat(errors.get(0).getErrorMessage(), is("There is no such priority!"));
    }
}
