package lv.javaguru.java2ToDoApp.businesslogic.impl;

import com.google.common.collect.Lists;
import lv.javaguru.java2ToDoApp.database.api.TaskDAO;
import lv.javaguru.java2ToDoApp.domain.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UpdateTaskValidator {

    @Autowired
    private TaskDAO taskDAO;

    private List<Error> errors = Lists.newArrayList();


    public UpdateTaskValidator validateTaskExists(Long id) {
        if (id == null) {
            errors.add(new Error("id", "Must not be empty!"));
        } else if (!taskExists(id)) {
            errors.add(new Error("id", "Such task dose not exists!"));
        }
        return this;
    }

    private boolean taskExists(Long id) {

        return taskDAO.getById(id).isPresent();
    }

    public UpdateTaskValidator validateDone(String done) {
        if (done != null && !(done.equalsIgnoreCase("true") || done.equalsIgnoreCase("false")) && !"".equals(done)) {
            errors.add(new Error("done", "This is not a boolean value!"));
        }
        return this;
    }

    public UpdateTaskValidator validateDueDate(String dueDate) {
        if (dueDate != null && validateDateFormat(dueDate) && !"".equals(dueDate)) {
            errors.add(new Error("dueDate", "This is not valid date format (yyyy-MM-dd)!"));
        }
        return this;
    }

    private boolean validateDateFormat(String value) {
        Date parsedDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            parsedDate = sdf.parse(value);
            return false;
        } catch (ParseException ex) {
            return true;
        }
    }

    public UpdateTaskValidator validatePriority(String priority) {
        if (priority != null && !(Priority.contains(priority)) && !"".equals(priority)) {
            errors.add(new Error("priority", "There is no such priority!"));
        }
        return this;
    }

    public List<Error> validate() {

        return errors;
    }
}
