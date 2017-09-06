package lv.javaguru.java2ToDoApp.common.tags;

import lv.javaguru.java2ToDoApp.common.util.TaskListUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class InputStyleTag extends SimpleTagSupport {

    /**
     * The task status.
     */
    private boolean status;

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        String inputStyle = TaskListUtils.getInputStyle(status);
        out.print(inputStyle);

    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
