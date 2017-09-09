package lv.javaguru.java2ToDoApp.controllers.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserGetService;
import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.common.util.TaskListUtils;
import lv.javaguru.java2ToDoApp.domain.Task;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserGetService userGetService;

    @Autowired
    private TaskGetService taskGetService;

    @RequestMapping(value = "/user/todos", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (request.getSession().getAttribute("user") == null) {
            User user = userGetService.getByLogin(auth.getName()).get();
            HttpSession session = request.getSession(true);//create session
            session.setAttribute("user", user);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Task> taskList = taskGetService.getAllTasksByUser(user);
        request.setAttribute("taskList", taskList);
        request.setAttribute("homeTabStyle", "active");

        int totalCount = taskList.size();
        int doneCount = TaskListUtils.countTotalDone(taskList);
        int taskCount = totalCount - doneCount;
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("doneCount", doneCount);
        request.setAttribute("taskCount", taskCount);
        return new ModelAndView("user/UserHome", "model", null);
    }
}
