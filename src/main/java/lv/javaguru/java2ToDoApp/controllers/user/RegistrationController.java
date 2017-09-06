package lv.javaguru.java2ToDoApp.controllers.user;

import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserGetService;
import lv.javaguru.java2ToDoApp.businesslogic.api.user.UserRegistrationService;
import lv.javaguru.java2ToDoApp.common.form.RegistrationForm;
import lv.javaguru.java2ToDoApp.domain.User;
import lv.javaguru.java2ToDoApp.domain.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserGetService userGetService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ResourceBundle resourceBundle;
    private Validator validator;

    public RegistrationController() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        resourceBundle = ResourceBundle.getBundle("todoAppMessages");
    }

    @RequestMapping(value = "register", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("registerTabStyle", "active");
        return new ModelAndView("user/UserRegistration", "model", null);
    }

    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String fullName = request.getParameter("name");
        String confirmationPassword = request.getParameter("confirmationPassword");

        System.out.println(login);

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setLogin(login);
        registrationForm.setFullName(fullName);
        registrationForm.setPassword(password);
        registrationForm.setConfirmationPassword(confirmationPassword);

        validateRegistrationForm(request, registrationForm);

        checkPasswordsMatch(request, password, confirmationPassword);

        if (isInvalid(request)) {
          //  System.out.println("isInvalid");
            return new ModelAndView("user/UserRegistration", "model", null);
        }

        if (isAlreadyUsed(login)) {
          //  System.out.println("isAlreadyUsed");
            request.setAttribute("error", MessageFormat.format(resourceBundle.getString("register.error.global.account"), login));
            return new ModelAndView("user/UserRegistration", "model", null);
        }

        User user = UserBuilder.createUser()
                .withLogin(login)
                .withFullName(fullName)
                .withPassword(bCryptPasswordEncoder.encode(password))
                .build();

        user = userRegistrationService.register(user);
        //HttpSession session = request.getSession(true);
        //session.setAttribute("user", user);
        //session.setAttribute("messageSuccess", "User registered successfully!");

        return new ModelAndView("redirect:/login", "model", null);
    }

    private boolean isAlreadyUsed(String login) {
        return userGetService.getByLogin(login).isPresent();
    }

    private boolean isInvalid(HttpServletRequest request) {
        return request.getAttribute("error") != null;
    }

    private void validateRegistrationForm(HttpServletRequest request, RegistrationForm registrationForm) {
        validateLogin(request, registrationForm);

        validateFullName(request, registrationForm);

        validatePassword(request, registrationForm);

        validateConfirmationPassword(request, registrationForm);
    }


    private void addGlobalRegistrationErrorAttribute(HttpServletRequest request) {
        request.setAttribute("error", resourceBundle.getString("register.error.global"));
    }

    private void validateLogin(HttpServletRequest request, RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "login");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorLogin", constraintViolations.iterator().next().getMessage());
          //  System.out.println("errorLogin " + request.getAttribute("errorLogin"));
            addGlobalRegistrationErrorAttribute(request);
        }
    }

    private void validateFullName(HttpServletRequest request, RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "fullName");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorFullName", constraintViolations.iterator().next().getMessage());
          //  System.out.println("errorFullName " + request.getAttribute("errorFullName"));
            addGlobalRegistrationErrorAttribute(request);
        }
    }

    private void validatePassword(HttpServletRequest request, RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "password");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorPassword", constraintViolations.iterator().next().getMessage());
          //  System.out.println("errorPassword " + request.getAttribute("errorPassword"));
            addGlobalRegistrationErrorAttribute(request);
        }
    }

    private void validateConfirmationPassword(HttpServletRequest request, RegistrationForm registrationForm) {
        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validateProperty(registrationForm, "confirmationPassword");
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errorConfirmationPassword", constraintViolations.iterator().next().getMessage());
          //  System.out.println("errorConfirmationPassword " + request.getAttribute("errorConfirmationPassword"));
            addGlobalRegistrationErrorAttribute(request);
        }
    }

    private void checkPasswordsMatch(HttpServletRequest request, String password, String confirmationPassword) {
        if (!confirmationPassword.equals(password)) {
            request.setAttribute("errorConfirmationPasswordMatching", resourceBundle.getString("register.error.password.confirmation.error"));
            addGlobalRegistrationErrorAttribute(request);
        }
    }
}