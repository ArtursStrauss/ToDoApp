package lv.javaguru.java2ToDoApp.controllers.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskUpdateService;
import lv.javaguru.java2ToDoApp.domain.Priority;
import lv.javaguru.java2ToDoApp.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UpdateTaskController {

    @Autowired
    private TaskGetService taskGetService;

    @Autowired
    private TaskUpdateService taskUpdateService;

    @RequestMapping(value = "user/todos/update", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("taskId");
        Long taskId = Long.parseLong(id);
        Task task = taskGetService.getTaskById(taskId).get(); // FIXME security : may provide an id for a todo of another user!
        request.setAttribute("task", task);
        return new ModelAndView("task/UpdateTask", "model", null);
    }

    @RequestMapping(value = "user/todos/update", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        Long taskId = Long.parseLong(request.getParameter("taskId"));
        String title = request.getParameter("title");
        String dueDate = request.getParameter("dueDate");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");

        Task task = taskGetService.getTaskById(taskId).get();

        task.setTitle(title);
        task.setDueDate(dueDate);
        task.setDone(Boolean.valueOf(status));
        task.setPriority(Priority.valueOf(priority));
        taskUpdateService.update(task);
        return new ModelAndView("redirect:/user/todos", "model", null);
    }
}

