package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.encryption.Encryptor;
import uitility.language.LanguageManager;
import uitility.validator.LoginValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Called when user tries to log in. Check if the login and password
 * are valid.
 */
public class UserLogin extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);
    private static final String TRUE = "TRUE";
    private static final String STUDENT = "Student";
    private static final String ADMIN = "Admin";

    private UserService userService;
    private LanguageManager languageManager;

    public UserLogin() {
        this.userService = new UserServiceImpl();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

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
        req.getSession().setAttribute("login", login);

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
