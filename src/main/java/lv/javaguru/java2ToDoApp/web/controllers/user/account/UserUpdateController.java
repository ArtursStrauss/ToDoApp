package lv.javaguru.java2ToDoApp.web.controllers.user.account;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserUpdateService;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserUpdateController {

    @Autowired
    private UserUpdateService userUpdateService;

    @RequestMapping(value = "/user/account/update", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        //Long id = Long.parseLong(request.getParameter("id"));
        String fullName = request.getParameter("name");

        //HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");

        user.setFullName(fullName);

        userUpdateService.update(user);

        return new ModelAndView("redirect:/user/account", "model", null);
    }
}
