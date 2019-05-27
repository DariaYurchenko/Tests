package controller.command;

import com.sun.mail.smtp.SMTPTransport;
import controller.pages.Pages;
import model.entity.User;
import model.service.impl.UserService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;
import java.util.Properties;

public class SendEmailAgain extends Command implements Pages {
    private UserService userService;

    public SendEmailAgain() {
        this.userService = new UserService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();

        String key = userService.getMagicKey(login);
        sendEmail(login, key);
        req.setAttribute("sent", "true");

        return CommandResult.forward(NOT_SUBMIT_EMAIL);
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
