package lv.javaguru.java2ToDoApp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AboutController {
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("About", "model", null);
    }
}
