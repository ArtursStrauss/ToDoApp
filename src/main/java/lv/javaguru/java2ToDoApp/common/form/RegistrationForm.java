package lv.javaguru.java2ToDoApp.common.form;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationForm {

    @NotEmpty(message = "{registration.error.login.required}")
    private String login;

    @NotEmpty(message = "{registration.error.fullName.required}")
    private String fullName;

    @NotEmpty(message = "{registration.error.password.required}")
    @Size(min = 6, message = "{registration.error.password.size}")
    private String password;

    @NotEmpty(message = "{registration.error.confirmationPassword.required}")
    @Size(min = 6, message = "{registration.error.confirmationPassword.size}")
    private String confirmationPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }
}
