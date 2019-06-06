package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import uitility.encryption.EncryptorBuilder;
import uitility.language.LanguageManager;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Called when user confirms forgetting password on the login page.
 */
public class ChangePassword extends Command implements CommandPages {
    private UserService userService;
    private LanguageManager languageManager;

    public ChangePassword() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String newPassword = req.getParameter("newPassword");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        /**
         * check if new password is valid
         * and doesn't change it if not
         */
        if(!validatePassword(newPassword)) {
            req.setAttribute("errPassword", languageManager.getMessage("incorrect_password"));
            return CommandResult.forward(CHANGE_PASSWORD_PAGE);
        }

        req.setAttribute("passwordChanged", "TRUE");

        changePassword(newPassword, login);

        return CommandResult.forward(LOGIN_PAGE);
    }

    private boolean validatePassword(String password) {
        return PasswordValidator.validatePassword(password);
    }

    private void changePassword(String newPassword, String login) {
        EncryptorBuilder builder = new EncryptorBuilder(newPassword);
        String hash = builder.getHash();
        byte[] salt = builder.getSalt();
        userService.changeUsersPassword(hash, salt, login);
    }

}
