package lv.javaguru.java2ToDoApp.controllers.task;

import lv.javaguru.java2ToDoApp.businesslogic.api.task.GetTasks;
import lv.javaguru.java2ToDoApp.businesslogic.api.task.TaskDeleteService;
import lv.javaguru.java2ToDoApp.domain.Task;
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
    private GetTasks getTasks;

    @Autowired
    private TaskDeleteService taskDeleteService;

    @RequestMapping(value = "user/todos/delete", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String taskId = request.getParameter("taskId");
        try {
            Long todoId = Long.parseLong(taskId);
            Optional<Task> task = getTasks.getTaskById(todoId);
            if (task.isPresent()) {
                taskDeleteService.delete(task.get());
                return new ModelAndView("redirect:/user/todos", "model", null);
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
