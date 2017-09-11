package lv.javaguru.java2ToDoApp.common.tags;


import lv.javaguru.java2ToDoApp.common.util.TaskListUtils;
import lv.javaguru.java2ToDoApp.core.domain.Priority;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PriorityIconTag extends SimpleTagSupport {

    /**
     * The todo's priority.
     */
    private String priority;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String priorityIcon = TaskListUtils.getPriorityIcon(Priority.valueOf(priority));
        out.print(priorityIcon);
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
