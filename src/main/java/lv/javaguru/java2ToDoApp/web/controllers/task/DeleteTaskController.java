package lv.javaguru.java2ToDoApp.web.controllers.task;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskGetService;
import lv.javaguru.java2ToDoApp.core.businesslogic.api.task.TaskDeleteService;
import lv.javaguru.java2ToDoApp.core.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class DeleteTaskController {

    @Autowired
    private TaskGetService taskGetService;

    @Autowired
    private TaskDeleteService taskDeleteService;

    @RequestMapping(value = "user/task/delete", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String taskId = request.getParameter("taskId");
        try {
            Long todoId = Long.parseLong(taskId);
            Optional<Task> task = taskGetService.getTaskById(todoId);
            if (task.isPresent()) {
                taskDeleteService.delete(task.get());
                return new ModelAndView("redirect:/user/tasks", "model", null);
            } else {
                // TODO implement error page
                return new ModelAndView("redirect:/", "model", null);
                //redirectToErrorPage(request, response, id);
            }
        } catch (NumberFormatException e) {
            // TODO implement error page
            return new ModelAndView("redirect:/", "model", null);
            //redirectToErrorPage(request, response, id);
        }
    }
}
