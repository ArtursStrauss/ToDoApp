package lv.javaguru.java2ToDoApp.web.controllers.user.account;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserDeleteService;
import lv.javaguru.java2ToDoApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserDeleteController {

    @Autowired
    private UserDeleteService userDeleteService;

    @RequestMapping(value = "/user/account/delete", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //if(user == null) {
        //    System.out.println("user null");
        //}
        userDeleteService.delete(user);

        session.invalidate();

        return new ModelAndView("redirect:/index", "model", null);
    }
}
