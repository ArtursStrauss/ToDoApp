package lv.javaguru.java2ToDoApp.controllers;

import lv.javaguru.java2ToDoApp.businesslogic.api.LoginService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("loginTabStyle", "active");
        return new ModelAndView("UserLogin", "model", null);
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        Response serviceResponse = service.login(request.getParameter("login"),
                request.getParameter("password"));

        if (serviceResponse.isSuccess()) {
            return new ModelAndView("redirect:/profile", "model", null);
        } else {
            return new ModelAndView("UserLogin", "map", serviceResponse.getErrors());
        }

    }
}
