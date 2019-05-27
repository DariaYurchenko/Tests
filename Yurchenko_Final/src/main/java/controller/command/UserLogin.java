package controller.command;

import controller.pages.Pages;
import model.entity.User;
import model.entity.entityenum.UserType;
import model.service.impl.UserService;
import org.apache.log4j.Logger;
import uitility.encryption.Encryptor;
import uitility.validator.LoginValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserLogin extends Command implements Pages {
    private static final Logger logger = Logger.getLogger(UserLogin.class);

    private UserService userService;

    public UserLogin(UserService userService) {
        this.userService = userService;
    }

    public UserLogin() {
        this.userService = new UserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if("TRUE".equals(req.getParameter("ifForgot"))) {
            req.setAttribute("forgot", "TRUE");
            req.getSession().setAttribute("login", login);
            return CommandResult.forward(LOGIN_PAGE);
        }

        //TODO: nullpointer if nothinf
        if(!validatePasswordLogin(req, login, password)) {
            return CommandResult.forward(LOGIN_PAGE);
        }

        Optional<User> userLogging = userService.findUserByLogin(login);
        if(!userLogging.isPresent()) {
            logger.warn("Someone tries to login without registration.");
            req.setAttribute("loginMessage", "There is no user with such login. Please, sign in.");
            return CommandResult.forward(LOGIN_PAGE);
        }
        User user = userLogging.get();



        boolean isCorrectPassword = Encryptor.verifyPassword(password, user.getHash(), user.getSalt());

        if(isCorrectPassword && user.getType().equals(UserType.STUDENT)) {
            req.getSession().setAttribute("name", user.getName());
            req.getSession().setAttribute("rank", user.getRank());
            req.getSession().setAttribute("user", user);
            return CommandResult.forward(TESTS);
        }
        if(isCorrectPassword && user.getType().equals(UserType.ADMIN)) {
            req.getSession().setAttribute("user", user);
            return CommandResult.forward(ADMIN_PAGE);
        }
        else {
            req.setAttribute("errPassword", "Password is not correct.");
            return CommandResult.forward(LOGIN_PAGE);
        }
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
