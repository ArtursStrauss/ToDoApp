package lv.javaguru.java2ToDoApp.controllers.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskCreateService;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.TaskBuilder;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CreateTaskController {

    @Autowired
    private TaskCreateService taskCreateService;

    @RequestMapping(value = "user/task/new", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return new ModelAndView("task/CreateTask", "model", null);
    }

    @RequestMapping(value = "user/task/new", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String dueDate = request.getParameter("dueDate");
        String priority = request.getParameter("priority");

        Task task = TaskBuilder.createTask()
                .withTitle(title)
                .withDueDate(dueDate)
                .withDone(false)
                .withPriority(priority)
                .withUserId(user.getId())
                .build();

        taskCreateService.create(task);

        return new ModelAndView("redirect:/user/tasks", "model", null);
    }
}
