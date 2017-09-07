package lv.javaguru.java2ToDoApp.controllers.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Error;
import lv.javaguru.java2ToDoApp.businesslogic.impl.Response;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @RequestMapping(value = "register", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("registerTabStyle", "active");
        return new ModelAndView("user/UserRegistration", "model", null);
    }

    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(@ModelAttribute("registrationForm") RegistrationForm registrationForm, HttpServletRequest request) {

        //System.out.println(registrationForm.getLogin());

        Response response = userRegistrationService.register(registrationForm);

        if (response.isFail()) {
            for (Map.Entry<String, Error> entry : response.getErrors().entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue().getErrorMessage());
            }
            return new ModelAndView("user/UserRegistration", "model", null);
        }

        return new ModelAndView("redirect:/login", "model", null);
    }
}