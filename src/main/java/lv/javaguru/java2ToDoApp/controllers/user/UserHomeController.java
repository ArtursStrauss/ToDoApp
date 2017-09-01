package lv.javaguru.java2ToDoApp.controllers.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.GetAllTasksService;
import lv.javaguru.java2ToDoApp.businesslogic.api.UserService;
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
public class UserHomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private GetAllTasksService getAllTasksService;

    @RequestMapping(value = "/user/todos", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (request.getSession().getAttribute("user") == null) {
            User user = userService.getByLogin(auth.getName());
            HttpSession session = request.getSession(true);//create session
            session.setAttribute("user", user);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println(user.toString());

        List<Task> taskList = getAllTasksService.getAllTasksByUser(user);
        request.setAttribute("taskList", taskList);
        request.setAttribute("homeTabStyle", "active");

        int totalCount = taskList.size();
        //int doneCount = TodoListUtils.countTotalDone(todoList);
        //int todoCount = totalCount - doneCount;
        request.setAttribute("totalCount", totalCount);
        //request.setAttribute("doneCount", doneCount);
        //request.setAttribute("todoCount", todoCount);

        return new ModelAndView("UserHome", "model", null);
    }
}
