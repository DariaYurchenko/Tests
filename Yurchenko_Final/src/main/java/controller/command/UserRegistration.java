package controller.command;

import controller.pages.Pages;
import model.entity.User;
import model.entity.entityenum.UserType;
import model.service.impl.UserService;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;
import uitility.validator.LoginValidator;
import uitility.encryption.EncryptorBuilder;
import uitility.validator.NameLastnameValidator;
import uitility.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserRegistration extends Command implements Pages {
    private static final Logger logger = Logger.getLogger(UserRegistration.class);

    private UserService userService;
    private LanguageManager languageManager;

    public UserRegistration() {
        this.userService = new UserService();
        this.languageManager =  LanguageManager.INSTANCE;
    }

    public UserRegistration(UserService userService) {
        this.userService = userService;
        this.languageManager =  LanguageManager.INSTANCE;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getParameter("appLocale"));

        languageManager.setLanguage(language);

        Optional<User> optionalUser = userService.findUserByLogin(login);
        if(optionalUser.isPresent()) {
            logger.warn("Unknown user attempted to register by existing email - " + login);
            req.setAttribute("user_exists", languageManager.getMessage("user_exists"));
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        boolean validParameters = validateParameters(req, name, lastname, login, password);
        if(!validParameters) {
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        User newUser = buildUser(name, lastname, login, password);

        userService.registerUser(newUser);
        req.getSession().setAttribute("user", newUser);

        return CommandResult.forward(TESTS);
    }

    private boolean validateParameters(HttpServletRequest req, String name, String lastName, String login, String password) {
        if(!LoginValidator.validateLogin(login)) {
            req.setAttribute("errLogin", languageManager.getMessage("incorrect_login"));
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

    private User buildUser(String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        return new User.Builder()
                .setName(name)
                .setLastName(lastname)
                .setLogin(login)
                .setHash(builder.getHash())
                .setSalt(builder.getSalt())
                .setUserType(UserType.STUDENT)
                .build();
    }

}
