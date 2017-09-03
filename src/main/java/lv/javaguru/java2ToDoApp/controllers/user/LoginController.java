package lv.javaguru.java2ToDoApp.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    //@Autowired
    //private LoginService service;
    //@Autowired
    //private UserService userService;

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginTabStyle", "active");

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // System.out.println(auth.toString());

        return new ModelAndView("user/UserLogin", "model", null);

    }
}
