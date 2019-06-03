package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import model.service.impl.UserServiceImpl;
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

public class UserRegistration extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserRegistration.class);
    private static final String STUDENT = "Student";
    private static final String ADMIN = "Admin";
    private static final String USER_TYPE_FOR_REGISTRATION = "userType";

    private UserServiceImpl userServiceImpl;
    private LanguageManager languageManager;

    public UserRegistration() {
        this.userServiceImpl = new UserServiceImpl();
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
        req.getSession().setAttribute("appLocale", language);

        User newUser;
        Optional<User> userOptional = userServiceImpl.findUserByLogin(login);
        if(userOptional.isPresent()) {
            LOGGER.warn("Unknown user attempted to register by existing email - " + login);
            req.setAttribute("user_exists", languageManager.getMessage("user_exists"));
            if("Admin".equals(req.getParameter("userType"))) return CommandResult.forward(SHOW_USERS);

            return CommandResult.forward(REGISTRATION_PAGE);
        }

        boolean validParameters = validateParameters(req, name, lastname, login, password);
        if(!validParameters) {
            if(ADMIN.equals(req.getParameter(USER_TYPE_FOR_REGISTRATION))) {
                return CommandResult.forward(SHOW_USERS);
            }
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        req.setAttribute("submitWindow", "TRUE");

        if(ADMIN.equals(req.getParameter(USER_TYPE_FOR_REGISTRATION))) {
            newUser = buildAdmin(req, name, lastname, login, password);
            finishUserRegistration(req, newUser, login, password);
            return CommandResult.forward(ADMIN_PAGE);
        }
        else {
            newUser = buildStudent(req, name, lastname, login, password);
            finishUserRegistration(req, newUser, login, password);
            return CommandResult.forward(TESTS);
        }

       /* User newUser = buildStudent(req, name, lastname, login, password);

        userServiceImpl.registerUser(newUser);

        sendEmailToConfirmRegistration(password, login);

        req.getSession().setAttribute("user", newUser);
        req.getSession().setAttribute("appLocale", language);

        setUserStatus(req, newUser);
        *//*if("Admin".equals(req.getParameter("userType"))) {
            return CommandResult.forward(SHOW_USERS);
        }*//*

        return CommandResult.forward(TESTS);*/
    }

    private void finishUserRegistration(HttpServletRequest req, User user, String login, String password) {
        userServiceImpl.registerUser(user);
        sendEmailToConfirmRegistration(password, login);
        req.getSession().setAttribute("user", user);

        setUserStatus(req, user);
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

    /*private User buildUser(HttpServletRequest req, String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        User user = new User.Builder()
                .withName(name)
                .withLastName(lastname)
                .withLogin(login)
                .withHash(builder.getHash())
                .withSalt(builder.getSalt())
                .build();

        switch(req.getParameter(USER_TYPE_FOR_REGISTRATION)) {
            case ADMIN: user.setStatus(UserStatus.ADMIN);
            case STUDENT: user.setStatus(UserStatus.STUDENT);
        }
        return user;
    }*/

    private User buildStudent(HttpServletRequest req, String name, String lastname, String login, String password) {
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

    private User buildAdmin(HttpServletRequest req, String name, String lastname, String login, String password) {
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

    private void setUserStatus(HttpServletRequest req, User user) {
        if(user.getStatus() == UserStatus.STUDENT) {
            req.getSession().setAttribute("userStatus", STUDENT);
        }
        if(user.getStatus() == UserStatus.ADMIN) {
            req.getSession().setAttribute("userStatus", ADMIN);
        }
    }

    private void sendEmailToConfirmRegistration(String password, String login) {
        String magicKey = generateMagicKey(password, login);
        MailsSender.sendEmailToConfirmRegistration(login, magicKey);
    }

    private String generateMagicKey(String password, String login) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        String magicKey = builder.getHash();
        userServiceImpl.addMagicKey(magicKey, login);
        return magicKey;
    }

}



