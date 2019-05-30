package controller.command;

import controller.pages.CommandPages;
import model.entity.User;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.encryption.EncryptorBuilder;
import uitility.language.LanguageManager;
import uitility.validator.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangePassword extends Command implements CommandPages {
    private UserServiceImpl userServiceImpl;
    private LanguageManager languageManager;

    public ChangePassword() {
        this.userServiceImpl = new UserServiceImpl();
        this.languageManager =  LanguageManager.INSTANCE;
    }

    public ChangePassword(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String newPassword = req.getParameter("newPassword");
        String language = String.valueOf(req.getParameter("appLocale"));

        languageManager.setLanguage(language);

        if(!validatePassword(req, newPassword)) {
            req.setAttribute("forgot", "TRUE");
            return CommandResult.forward(LOGIN_PAGE);
        }

        changePassword(newPassword, login);

        req.setAttribute("passwordChanged",  languageManager.getMessage("password_changed"));
        return CommandResult.forward(LOGIN_PAGE);
    }

    private boolean validatePassword(HttpServletRequest req, String password) {
        if(!PasswordValidator.validatePassword(password)) {
            req.setAttribute("errPassword",  languageManager.getMessage("incorrect_password"));
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
        userServiceImpl.changeUsersPassword(hash, salt, login);
    }
}
