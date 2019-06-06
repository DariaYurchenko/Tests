package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;
import uitility.mail.MailsSender;
import uitility.validator.LoginValidator;
import uitility.encryption.EncryptorBuilder;
import uitility.validator.NameLastnameValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Register user.
 */
public class UserRegistration extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserRegistration.class);
    private static final String STUDENT = "Student";
    private static final String ADMIN = "Admin";

    private UserService userService;
    private LanguageManager languageManager;

    public UserRegistration() {
        this.userService = ServiceFactory.getInstance().getUserService();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        Optional<User> userOptional = userService.findUserByLogin(login);
        if(userOptional.isPresent()) {
            LOGGER.warn("Unknown user attempted to register by existing email - " + login);
            req.setAttribute("user_exists", languageManager.getMessage("user_exists"));
            return redirectAccordingToUserType(req);
        }

        boolean validParameters = validateParameters(req, name, lastname, login, password);
        if(!validParameters) {
            return redirectAccordingToUserType(req);
        }

        return registerUser(req, login, password, name, lastname);
    }

    private CommandResult registerUser(HttpServletRequest req, String login, String password, String name, String lastname) {
        User newUser;
        if(ADMIN.equals(req.getParameter("userType"))) {
            newUser = buildAdmin(name, lastname, login, password);
            finishUserRegistration(req, newUser, login, password);
            return CommandResult.forward(ADMIN_PAGE);
        }
        else {
            newUser = buildStudent(name, lastname, login, password);
            finishUserRegistration(req, newUser, login, password);
            return CommandResult.forward(TESTS);
        }
    }

    private void finishUserRegistration(HttpServletRequest req, User user, String login, String password) {
        userService.registerUser(user);
        sendEmailToConfirmRegistration(req, password, login);
        setUserInSession(req, login);
        req.setAttribute("submitWindow", "TRUE");
        setUserStatus(req, user);
    }

    private void setUserInSession(HttpServletRequest req, String login) {
        userService.findUserByLogin(login)
                .ifPresent(user -> req.getSession().setAttribute("user", user));
    }

    private boolean validateParameters(HttpServletRequest req, String name, String lastName, String login, String password) {
        if(!LoginValidator.validateLogin(login)) {
            req.setAttribute("errLogin", languageManager.getMessage("incorrect_login"));
            return false;
        }
        if(!NameLastnameValidator.validateNameLastname(name) && !NameLastnameValidator.validateNameLastname(lastName)) {
            req.setAttribute("errLastname", languageManager.getMessage("incorrect_lastname"));
            req.setAttribute("errName", languageManager.getMessage("incorrect_name"));
            return false;
        }
        if(!NameLastnameValidator.validateNameLastname(name)) {
            req.setAttribute("errName", languageManager.getMessage("incorrect_name"));
            return false;
        }
        if(!NameLastnameValidator.validateNameLastname(lastName)) {
            req.setAttribute("errLastname", languageManager.getMessage("incorrect_lastname"));
            return false;
        }
        if(!PasswordValidator.validatePassword(password)) {
            req.setAttribute("errPassword", languageManager.getMessage("incorrect_password"));
            return false;
        }
        return true;
    }

    private User buildStudent(String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        return new User.Builder()
                .withName(name)
                .withLastName(lastname)
                .withUserType(UserStatus.STUDENT)
                .withLogin(login)
                .withHash(builder.getHash())
                .withSalt(builder.getSalt())
                .build();
    }

    private User buildAdmin(String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        return new User.Builder()
                .withName(name)
                .withLastName(lastname)
                .withUserType(UserStatus.ADMIN)
                .withLogin(login)
                .withHash(builder.getHash())
                .withSalt(builder.getSalt())
                .build();
    }

    private CommandResult redirectAccordingToUserType(HttpServletRequest req) {
        if(ADMIN.equals(req.getParameter("userType"))) {
            return CommandResult.forward(ADMIN_USERS);
        }
        return CommandResult.forward(REGISTRATION_PAGE);
    }

    private void setUserStatus(HttpServletRequest req, User user) {
        if(user.getUserStatus() == UserStatus.STUDENT) {
            req.getSession().setAttribute("userStatus", STUDENT);
        }
        if(user.getUserStatus() == UserStatus.ADMIN) {
            req.getSession().setAttribute("userStatus", ADMIN);
        }
    }

    private void sendEmailToConfirmRegistration(HttpServletRequest req, String password, String login) {
        String magicKey = generateMagicKey(password, login);
        String language = (String) req.getSession().getAttribute("appLocale");
        MailsSender.sendEmailToConfirmRegistration(login, magicKey, language);
    }

    private String generateMagicKey(String password, String login) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        String magicKey = builder.getHash();
        userService.addMagicKey(magicKey, login);
        return magicKey;
    }

}
