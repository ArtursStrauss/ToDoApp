package lv.javaguru.java2ToDoApp.common.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class ChangePasswordForm {

    @NotEmpty(message = "{update.currentPassword.required}")
    @Size(min = 6, message = "{update.currentPassword.size}")
    private String currentPassword;

    @NotEmpty(message = "{update.newPassword.required}")
    @Size(min = 6, message = "{update.newPassword.size}")
    private String newPassword;

    @NotEmpty(message = "{update.confirmationPassword.required}")
    @Size(min = 6, message = "{update.confirmationPassword.size}")
    private String confirmationPassword;

    private String login;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}