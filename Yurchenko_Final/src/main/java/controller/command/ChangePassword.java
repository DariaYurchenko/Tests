package controller.command;

import controller.pages.Pages;
import model.entity.User;
import model.service.impl.UserService;
import org.apache.log4j.Logger;
import uitility.encryption.EncryptorBuilder;
import uitility.validator.LoginValidator;
import uitility.validator.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangePassword extends Command implements Pages {
    private static final Logger logger = Logger.getLogger(UserRegistration.class);

    private UserService userService;

    public ChangePassword() {
        this.userService = new UserService();
    }

    public ChangePassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String newPassword = req.getParameter("newPassword");

        if(!validatePasswordLogin(req, login, newPassword)) {
            return CommandResult.forward(CHANGE_PASSWORD_PAGE);
        }

        Optional<User> userLogging = userService.findUserByLogin(login);
        if(!userLogging.isPresent()) {
            logger.warn("Someone tries to login without registration.");
            req.setAttribute("loginMessage", "There is no user with such login. Please, sign in.");
            return CommandResult.forward(CHANGE_PASSWORD_PAGE);
        }

        EncryptorBuilder builder = new EncryptorBuilder(newPassword);
        String hash = builder.getHash();
        byte[] salt = builder.getSalt();
        userService.changeUsersPassword(newPassword, salt, hash);

        req.setAttribute("passwordChanged", "Your password was successfully changed.");
        return CommandResult.forward(CHANGE_PASSWORD_PAGE);
    }

    private boolean validatePasswordLogin(HttpServletRequest req, String login, String password) {
        if(!LoginValidator.validateLogin(login)) {
            req.setAttribute("errLogin", "Login is incorrect.");
            return false;
        }
        if(!PasswordValidator.validatePassword(password)) {
            req.setAttribute("errPassword", "Password is incorrect. Forgot it?");
            return false;
        }
        else {
            return true;
        }
    }
}
