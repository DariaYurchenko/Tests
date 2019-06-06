package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Test;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.TestService;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import uitility.encryption.Encryptor;
import uitility.language.LanguageManager;
import uitility.validator.LoginValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Called when user tries to log in. Check if the login and password
 * are valid. If Password is valid but incorrect, proposes user
 * to change it by putting in new one.
 */
public class UserLogin extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);
    private static final String TRUE = "TRUE";
    private static final String STUDENT = "Student";
    private static final String ADMIN = "Admin";

    private UserService userService;
    private LanguageManager languageManager;

    public UserLogin() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        /**
         * if user forgot password (put in incorrect but valid one),
         * proposes him to change it putting in in additional field
         */
        if (TRUE.equals(req.getParameter("ifForgotPassword"))) {
            req.setAttribute("forgot", TRUE);
            req.getSession().setAttribute("login", login);
            return CommandResult.forward(CHANGE_PASSWORD_PAGE);
        }

        if (!validatePasswordLogin(req, login, password)) {
            return CommandResult.forward(LOGIN_PAGE);
        }

        Optional<User> userLogging = userService.findUserByLogin(login);

        if (!userLogging.isPresent()) {
            LOGGER.warn("Someone tries to login without registration.");
            req.setAttribute("loginMessage", languageManager.getMessage("no_user"));
            return CommandResult.forward(LOGIN_PAGE);
        }

        User user = userLogging.get();
        setUserStatus(req, user);

        return redirectToPage(req, user, password);
    }

    private boolean validatePasswordLogin(HttpServletRequest req, String login, String password) {
        if(!LoginValidator.validateLogin(login)) {
            req.setAttribute("errLogin", languageManager.getMessage("incorrect_login"));
            return false;
        }
        if(!PasswordValidator.validatePassword(password)) {
            req.setAttribute("errPassword", languageManager.getMessage("incorrect_password"));
            return false;
        }
        else {
            return true;
        }
    }

    private void setUserStatus(HttpServletRequest req, User user) {
        if(user.getUserStatus() == UserStatus.STUDENT) {
            req.getSession().setAttribute("userStatus", STUDENT);
        }
        if(user.getUserStatus() == UserStatus.ADMIN) {
            req.getSession().setAttribute("userStatus", ADMIN);
        }
    }

    private CommandResult redirectToPage(HttpServletRequest req, User user, String password) {
        if (verifyPassword(user, password)) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("name", user.getName());
            return CommandResult.forward(START_PAGE);
        }
        else {
            req.setAttribute("forgotPassword", languageManager.getMessage("icorrect_password_login"));
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private boolean verifyPassword(User user, String password) {
        return Encryptor.verifyPassword(password, user.getHash(), user.getSalt());
    }

}
