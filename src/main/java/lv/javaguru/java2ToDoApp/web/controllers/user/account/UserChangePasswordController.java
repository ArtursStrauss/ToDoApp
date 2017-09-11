package lv.javaguru.java2ToDoApp.web.controllers.user.account;

import lv.javaguru.java2ToDoApp.core.businesslogic.api.user.UserChangePasswordService;
import lv.javaguru.java2ToDoApp.common.Error;
import lv.javaguru.java2ToDoApp.common.Response;
import lv.javaguru.java2ToDoApp.common.form.ChangePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class UserChangePasswordController {

    private ResourceBundle resourceBundle;

    @Autowired
    private UserChangePasswordService userChangePasswordService;

    @RequestMapping(value = "/user/account/password", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        changePasswordForm.setLogin(aut.getName());

        Response response = userChangePasswordService.change(changePasswordForm);

        if (response.isFail()) {
            for (Map.Entry<String, Error> entry : response.getErrors().entrySet()) {
                redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue().getErrorMessage());
            }
        } else {
            resourceBundle = ResourceBundle.getBundle("todoAppMessages");
            redirectAttributes.addFlashAttribute("updatePasswordSuccessMessage", resourceBundle.getString("account.password.update.success"));
        }
        return new ModelAndView("redirect:/user/account", "model", null);
    }
}
