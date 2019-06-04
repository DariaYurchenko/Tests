package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Test;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.TestService;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import model.service.impl.TestServiceImpl;
import model.service.impl.UserServiceImpl;
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

public class UserLogin extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);

    private static final String USER_FORGOT_PASSWORD = "ifForgotPassword";
    private static final String DID_FORGET = "forgot";
    private static final String TRUE = "TRUE";
    private static final String USER_STATUS = "userStatus";
    private static final String LOGIN_MESSAGE = "loginMessage";

    private UserService userService;
    private TestService testService;
    private LanguageManager languageManager;

    public UserLogin() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.testService = ServiceFactory.getInstance().getTestService();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        if (TRUE.equals(req.getParameter(USER_FORGOT_PASSWORD))) {
            req.setAttribute(DID_FORGET, TRUE);
            req.getSession().setAttribute("login", login);
            return CommandResult.forward(LOGIN_PAGE);
        }

        if (!validatePasswordLogin(req, login, password)) {
            return CommandResult.forward(LOGIN_PAGE);
        }

        Optional<User> userLogging = userService.findUserByLogin(login);

        if (!userLogging.isPresent()) {
            LOGGER.warn("Someone tries to login without registration.");
            req.setAttribute(LOGIN_MESSAGE, languageManager.getMessage("no_user"));
            return CommandResult.forward(LOGIN_PAGE);
        }

        User user = userLogging.get();
        setUserStatus(req, user);
        /////////////////////////////////////////////////
        List<Test> tests = testService.findTestsByParameter("test_user_id", user.getUserId());
        List<Long> themesId = new ArrayList<>();
        for(Test test : tests) {
            themesId.add(test.getThemeId());
        }
        req.getSession().setAttribute("userThemes", themesId);


        ////////////////////////////////////////////////

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
            req.getSession().setAttribute(USER_STATUS, "Student");
        }
        if(user.getStatus() == UserStatus.ADMIN) {
            req.getSession().setAttribute(USER_STATUS, "Admin");
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
