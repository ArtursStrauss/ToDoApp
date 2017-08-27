package lv.javaguru.java2ToDoApp.controllers;

import lv.javaguru.java2ToDoApp.businesslogic.api.LoginService;
import lv.javaguru.java2ToDoApp.businesslogic.api.UserService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginTabStyle", "active");
        return new ModelAndView("UserLogin", "model", null);
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Response serviceResponse = service.login(login,password);

        if (serviceResponse.isSuccess()) {
            HttpSession session = request.getSession(true);//create session
            User user = userService.getByLogin(login);
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/profile", "model", null);
        } else {
            return new ModelAndView("UserLogin", "map", serviceResponse.getErrors());
        }

    }
}
