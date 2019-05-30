package controller.command;

import com.sun.mail.smtp.SMTPTransport;
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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Optional;
import java.util.Properties;

public class UserRegistration extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserRegistration.class);

    private UserServiceImpl userServiceImpl;
    private LanguageManager languageManager;

    public UserRegistration() {
        this.userServiceImpl = new UserServiceImpl();
        this.languageManager =  LanguageManager.INSTANCE;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String language = String.valueOf(req.getSession().getAttribute("appLocale"));

        languageManager.setLanguage(language);

        Optional<User> userOptional = userServiceImpl.findUserByLogin(login);
        if(userOptional.isPresent()) {
            LOGGER.warn("Unknown user attempted to register by existing email - " + login);
            req.setAttribute("user_exists", languageManager.getMessage("user_exists"));
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        boolean validParameters = validateParameters(req, name, lastname, login, password);
        if(!validParameters) {
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        User newUser = buildUser(req, name, lastname, login, password);

        userServiceImpl.registerUser(newUser);

        sendEmailToConfirmRegistration(password, login);

        req.getSession().setAttribute("user", newUser);
        req.getSession().setAttribute("appLocale", language);

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

    private User buildUser(HttpServletRequest req, String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        User user = new User.Builder()
                .withName(name)
                .withLastName(lastname)
                .withLogin(login)
                .withHash(builder.getHash())
                .withSalt(builder.getSalt())
                .build();

        if("ADMIN".equals(req.getParameter("userType"))) {
            user.setStatus(UserStatus.ADMIN);
        }
        else {
            user.setStatus(UserStatus.STUDENT);
        }
        return user;
    }

    private void sendEmailToConfirmRegistration(String password, String login) {
        String magicKey = generateMagicKey(password, login);
        MailsSender.sendEmailToConfirmRegistration(login, magicKey);
    }

    private String generateMagicKey(String password, String login) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        String magicKey = builder.getHash();
        userServiceImpl.addKey(magicKey, login);
        return magicKey;
    }

}



