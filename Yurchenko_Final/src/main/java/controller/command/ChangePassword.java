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

public class ChangePassword extends Command implements CommandPages {
    private static final String DID_FORGET = "forgot";
    private static final String TRUE = "TRUE";

    private UserService userService;
    private LanguageManager languageManager;

    public ChangePassword() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.languageManager =  LanguageManager.getInstance();
    }

    public ChangePassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String newPassword = req.getParameter("newPassword");
        String language = String.valueOf(req.getParameter("appLocale"));

        //languageManager.setLanguage(language);

        if(!validatePassword(req, newPassword)) {
            req.setAttribute(DID_FORGET, TRUE);
            return CommandResult.forward(LOGIN_PAGE);
        }


        changePassword(newPassword, login);

        //req.setAttribute("passwordChanged",  languageManager.getMessage("password_changed"));
        return CommandResult.forward(LOGIN_PAGE);
    }

    private boolean validatePassword(HttpServletRequest req, String password) {
        if(!PasswordValidator.validatePassword(password)) {
            //req.setAttribute("errPassword",  languageManager.getMessage("incorrect_password"));
            return false;
        }
        else {
            return true;
        }
    }

    private void changePassword(String newPassword, String login) {
        EncryptorBuilder builder = new EncryptorBuilder(newPassword);
        String hash = builder.getHash();
        byte[] salt = builder.getSalt();
        userService.changeUsersPassword(hash, salt, login);
    }
}
