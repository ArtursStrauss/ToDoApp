package lv.javaguru.java2ToDoApp.controllers.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskSearchController {

    @Autowired
    private TaskGetService taskGetService;

    @RequestMapping(value = "user/task/search", method = {RequestMethod.GET})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String title = request.getParameter("title");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Task> taskList = taskGetService.getTaskListByUserAndTitle(user, title);
        request.setAttribute("taskList", taskList);

        return new ModelAndView("/user/UserHome", "model", null);
    }
}
