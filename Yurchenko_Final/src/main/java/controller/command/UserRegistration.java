package controller.command;

import com.sun.mail.smtp.SMTPTransport;
import controller.pages.Pages;
import model.entity.User;
import model.entity.entityenum.UserType;
import model.service.impl.UserService;
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

        User newUser = buildUser(req, name, lastname, login, password);

        userService.registerUser(newUser);

        User user = userService.findUserByLogin(login).get();
        Long id = user.getUserId();
        String key = generateMagicKey(password);
        userService.addKey(key, login);

        sendEmail(login, key);

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

    private User buildUser(HttpServletRequest req, String name, String lastname, String login, String password) {
        EncryptorBuilder builder = new EncryptorBuilder(password);
        if("ADMIN".equals(req.getParameter("userType"))) {
            return new User.Builder()
                    .setName(name)
                    .setLastName(lastname)
                    .setLogin(login)
                    .setHash(builder.getHash())
                    .setSalt(builder.getSalt())
                    .setUserType(UserType.ADMIN)
                    .build();
        }
        return new User.Builder()
                .setName(name)
                .setLastName(lastname)
                .setLogin(login)
                .setHash(builder.getHash())
                .setSalt(builder.getSalt())
                .setUserType(UserType.STUDENT)
                .build();
    }

    private String generateMagicKey(String password) {
        try {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(salt);
        byte[] digest = messageDigest.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
        }  catch (NoSuchAlgorithmException e) {
            throw new NullPointerException();
        }
    }

    private void sendEmail(String login, String key) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = System.getProperties();
            props.setProperty("mail.smtps.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.socketFactory.port", "587");
            props.setProperty("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");

            Session session = Session.getInstance(props, null);

            final MimeMessage msg = new MimeMessage(session);

            String href = "http://localhost:8081/Yurchenko_Final_war_exploded/tests?command=SUBMIT_KEY&login=" + login +
                    "&key=" + key;

            msg.setFrom(new InternetAddress("yurchenkod2017" + "@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(login, false));
            msg.setSubject("Reg");
            msg.setContent("<a href=\"" + href + "\">Pass</a>", "text/html; charset=utf-8");

            SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

            t.connect("smtp.gmail.com", "yurchenkod2017", "230da68sha19");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {

        }
    }
    }


