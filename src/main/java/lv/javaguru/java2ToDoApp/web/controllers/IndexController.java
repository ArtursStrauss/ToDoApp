package lv.javaguru.java2ToDoApp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView processRootRequest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("Index", "model", "Hello from MVC");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String processIndexRequest(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/";
    }
}
