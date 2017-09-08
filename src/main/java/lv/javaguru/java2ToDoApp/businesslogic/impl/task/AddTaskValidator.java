package lv.javaguru.java2ToDoApp.businesslogic.impl.task;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.domain.Priority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AddTaskValidator {

    private Optional<Error> validateTitle(String title) {
        if (title == null || "".equals(title)) {
            return Optional.of(new Error("title", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDone(String done) {
        if (done == null || "".equals(done)) {
            return Optional.of(new Error("done", "Must not be empty!"));
        } else if (!(done.equalsIgnoreCase("true") || done.equalsIgnoreCase("false"))) {
            return Optional.of(new Error("done", "This is not a boolean value!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validatePriority(String priority) {
        if (priority == null || "".equals(priority)) {
            return Optional.of(new Error("priority", "Must not be empty!"));
        } else if (!(Priority.contains(priority))) {
            return Optional.of(new Error("priority", "There is no such priority!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDueDate(String dueDate) {
        if (dueDate == null || "".equals(dueDate)) {
            return Optional.of(new Error("dueDate", "Must not be empty!"));
        } else if (validateDateFormat(dueDate)) {
            return Optional.of(new Error("dueDate", "This is not valid date format (yyyy-MM-dd)!"));
        } else {
            return Optional.empty();
        }
    }

    private boolean validateDateFormat(String value) {
        Date parsedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            parsedDate = sdf.parse(value);
        } catch (ParseException ex) {
            return true;
        }
        return false;
    }

    public List<Error> validate(String title, String done, String dueDate, String priority) {
        List<Error> errors = Lists.newArrayList();
        validateTitle(title).ifPresent(e -> errors.add(e));
        validateDone(done).ifPresent(e -> errors.add(e));
        validateDueDate(dueDate).ifPresent(e -> errors.add(e));
        validatePriority(priority).ifPresent(e -> errors.add(e));

        return errors;
    }
}
