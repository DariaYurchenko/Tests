package uitility.mail;

import com.sun.mail.smtp.SMTPTransport;
import model.entity.TestInfo;
import org.apache.log4j.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public final class MailsSender {
    private static final Logger LOGGER = Logger.getLogger(MailsSender.class);

    private static final String SEND_FROM = "yurchenkod2017";
    private static final String PASSWORD = "230da68sha19";
    private static final String SUBJECT = "Test's results";
    private static final String PROTOCOL = "smtps";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String CONTENT = "<h1>Dear %s!</h1><h2>The results of the test ont theme %s percent:</h2>" +
            "<p>You've got %s points from %s having scored %s  of max amount of points.</p><p>The test is %s." +
            "</p><p>The date of the test is %s.</p><p>Now your rank is %s percent.</p>";

    public static void send(TestInfo testInfo) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = System.getProperties();
            putProperties(props);

            Session session = Session.getInstance(props, null);

            final MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(SEND_FROM + "@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(testInfo.getUserLogin(), false));
            msg.setSubject(SUBJECT);
            msg.setContent(makeMessage(CONTENT, testInfo), "text/html; charset=utf-8");

            SMTPTransport t = (SMTPTransport)session.getTransport(PROTOCOL);

            t.connect("smtp.gmail.com", SEND_FROM, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            LOGGER.error("MessagingException while sending email: " + e.getMessage());
        }
    }
    private static String makeMessage(String content, TestInfo testInfo) {
        return String.format(content, testInfo.getUserName() + " " + testInfo.getUserLastName(), testInfo.getTheme(),
                testInfo.getUserPoints().toString(), testInfo.getMaxPoints().toString(),
                testInfo.getRightAnswersPercent().toString(), "passed", convertLocalDateToString(testInfo),
                testInfo.getUserRank());
    }

    private static String convertLocalDateToString(TestInfo testInfo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        LocalDate localDate = testInfo.getDate();
        return localDate.format(formatter);
    }

    private static void putProperties(Properties props) {
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
    }
}