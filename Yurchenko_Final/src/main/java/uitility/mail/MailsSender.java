package uitility.mail;

import com.sun.mail.smtp.SMTPTransport;
import exception.MailsSenderException;
import model.entity.TestInfo;
import model.entity.status.TestStatus;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class MailsSender {
    private static final Logger LOGGER = Logger.getLogger(MailsSender.class);

    private static final String SEND_FROM = "yurchenkod2017";
    private static final String PASSWORD = "230da68sha19";
    private static final String PROTOCOL = "smtps";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String CONTENT_CONFIRM_EMAIL = "<a href=\"%s\">Confirm your email!</a>";
    private static final String HREF_CONFIRM_EMAIL = "http://localhost:8081/tests?command=SUBMIT_KEY&login=%s&key=%s";

    private static LanguageManager languageManager = LanguageManager.getInstance();

    public static void sendTestResults(TestInfo testInfo) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            //Todo:properties
            Properties props = System.getProperties();
            setProperties(props);

            Session session = Session.getInstance(props, null);

            final MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(SEND_FROM + "@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(testInfo.getUserLogin(), false));
            msg.setSubject(languageManager.getMessage("tests_subject"));
            msg.setContent(makeMessageTestResults(testInfo), "text/html; charset=utf-8");

            SMTPTransport t = (SMTPTransport)session.getTransport(PROTOCOL);

            t.connect("smtp.gmail.com", SEND_FROM, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            LOGGER.error("MessagingException while sending test results by email: " + e.getMessage());
            throw new MailsSenderException(e);
        }
    }

    private static String makeMessageTestResults(TestInfo testInfo) {
        return String.format(languageManager.getMessage("email_results"), testInfo.getUserName() + " " + testInfo.getUserLastName(), testInfo.getTheme(),
                testInfo.getUserPoints().toString(), testInfo.getMaxPoints().toString(),
                testInfo.getRightAnswersPercent().toString(), checkTestStatus(testInfo), convertLocalDateToString(testInfo),
                testInfo.getUserRank());
    }

    private static String checkTestStatus (TestInfo testInfo) {
        if(testInfo.getTestStatus().equals(TestStatus.PASSED)) {
            return languageManager.getMessage("passed");
        }
        else {
            return languageManager.getMessage("not_passed");
        }
    }

    //Todo: constatnts
    private static String convertLocalDateToString(TestInfo testInfo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        LocalDate localDate = testInfo.getDate();
        return localDate.format(formatter);
    }


    //TODO: Runtime exception
    public static void sendEmailToConfirmRegistration(String login, String magicKey) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = System.getProperties();
            setProperties(props);

            Session session = Session.getInstance(props, null);

            final MimeMessage msg = new MimeMessage(session);

            String href = String.format(HREF_CONFIRM_EMAIL, login, magicKey);
            String content = String.format(CONTENT_CONFIRM_EMAIL, href);

            msg.setFrom(new InternetAddress(SEND_FROM + "@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(login, false));
            msg.setSubject(languageManager.getMessage("confirm_subject"));
            msg.setContent(content, "text/html; charset=utf-8");

            SMTPTransport t = (SMTPTransport) session.getTransport(PROTOCOL);

            t.connect("smtp.gmail.com", SEND_FROM, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        }catch (MessagingException e) {
            LOGGER.error("MessagingException while sending magic key to confirm email: " + e.getMessage());
            throw new MailsSenderException(e);
        }
    }

    private static void setProperties(Properties props) {
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
    }
}