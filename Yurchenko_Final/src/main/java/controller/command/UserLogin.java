package controller.command;

import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.encryption.Encryptor;
import uitility.language.LanguageManager;
import uitility.validator.LoginValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserLogin extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);

    private UserServiceImpl userServiceImpl;
    private LanguageManager languageManager;

    public UserLogin() {
        this.userServiceImpl = new UserServiceImpl();
        this.languageManager =  LanguageManager.INSTANCE;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        if ("TRUE".equals(req.getParameter("ifForgotPassword"))) {
            req.setAttribute("forgot", "TRUE");
            req.getSession().setAttribute("login", login);
            return CommandResult.forward(LOGIN_PAGE);
        }

        if (!validatePasswordLogin(req, login, password)) {
            return CommandResult.forward(LOGIN_PAGE);
        }

        Optional<User> userLogging = userServiceImpl.findUserByLogin(login);
      /*  userLogging.ifPresent(()->{User user = userLogging.get();
            setUserStatus(req, user);
            return checkUserStatus(req, user, password);});*/
        if (!userLogging.isPresent()) {
            LOGGER.warn("Someone tries to login without registration.");
            req.setAttribute("loginMessage", languageManager.getMessage("no_user"));
            return CommandResult.forward(LOGIN_PAGE);
        }

        User user = userLogging.get();
        setUserStatus(req, user);
        return checkUserStatus(req, user, password);
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
        if(user.getStatus() == UserStatus.STUDENT) {
            req.getSession().setAttribute("userStatus", "Student");
        }
        if(user.getStatus() == UserStatus.ADMIN) {
            req.getSession().setAttribute("userStatus", "Admin");
        }
    }

    private CommandResult checkUserStatus(HttpServletRequest req, User user, String password) {
        boolean isCorrectPassword = Encryptor.verifyPassword(password, user.getHash(), user.getSalt());
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("name", user.getName());

        if (isCorrectPassword && user.getStatus().equals(UserStatus.STUDENT)) {
            return CommandResult.forward(TESTS);
        }
        if (isCorrectPassword && user.getStatus().equals(UserStatus.ADMIN)) {
            return CommandResult.forward(ADMIN_PAGE);
        } else {
            req.setAttribute("forgotPassword", languageManager.getMessage("icorrect_password_login"));
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
