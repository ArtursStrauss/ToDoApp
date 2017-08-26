package lv.javaguru.java2ToDoApp.controllers;

import lv.javaguru.java2ToDoApp.businesslogic.api.RegisterUserService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserRegistrationController {

    @Autowired
    private RegisterUserService service;

    @RequestMapping(value = "register", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("UserRegistration", "model", null);
    }

    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        Response serviceResponse = service.register(request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("confirmationPassword"));
        if (serviceResponse.isSuccess()) {
            request.getSession().setAttribute("messageSuccess", "User registered successfully!");
            return new ModelAndView("redirect:/", "model", null);
        } else {
            return new ModelAndView("UserRegistration", "map", serviceResponse.getErrors());
        }
    }
}
