package uitility.mail;

import com.sun.mail.smtp.SMTPTransport;
import exception.MailsSenderRuntimeException;
import model.entity.TestInfo;
import model.entity.status.TestStatus;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class MailsSender {
    private static final Logger LOGGER = Logger.getLogger(MailsSender.class);
    private static final LanguageManager languageManager = LanguageManager.getInstance();

    private MailsSender() {
    }

    public static void sendTestResults(TestInfo testInfo, String language) {
        try {
            Properties propertyRead = readPropertyFile();
            languageManager.setLanguage(language);

            String login = testInfo.getUserLogin();
            String content = makeMessageTestResults(testInfo, propertyRead);
            String subject = languageManager.getMessage("tests_subject");

            setUpMailSend(propertyRead, login, content, subject);

        } catch (MessagingException e) {
            LOGGER.error("MessagingException while sending test results by email: " + e.getMessage());
            throw new MailsSenderRuntimeException(e);
        } catch (IOException e) {
            LOGGER.error("IOException while sending magic key to confirm email: " + e.getMessage());
            throw new MailsSenderRuntimeException(e);
        }
    }

    public static void sendEmailToConfirmRegistration(String login, String magicKey, String language) {
        try {
            Properties propertyRead = readPropertyFile();
            languageManager.setLanguage(language);

            String href = String.format(propertyRead.getProperty("href.confirm.email"), login, magicKey);
            String content = String.format(propertyRead.getProperty("content.confirm.email"), href);

            String subject = languageManager.getMessage("confirm_subject");
            setUpMailSend(propertyRead, login, content, subject);

        } catch (MessagingException e) {
            LOGGER.error("MessagingException while sending magic key to confirm email: " + e.getMessage());
            throw new MailsSenderRuntimeException(e);
        } catch (IOException e) {
            LOGGER.error("IOException while sending magic key to confirm email: " + e.getMessage());
            throw new MailsSenderRuntimeException(e);
        }
    }

    private static String makeMessageTestResults(TestInfo testInfo, Properties propertyRead) {
        return String.format(languageManager.getMessage("email_results"), testInfo.getUserName() + " "
                        + testInfo.getUserLastName(), testInfo.getTheme(),
                testInfo.getUserPoints().toString(), testInfo.getMaxPossiblePoints().toString(),
                testInfo.getRightAnswersPercent().toString(), checkTestStatus(testInfo), convertLocalDateToString(testInfo, propertyRead),
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

    private static String convertLocalDateToString(TestInfo testInfo, Properties propertiesRead) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(propertiesRead.getProperty("data"));
        LocalDate localDate = testInfo.getDate();
        return localDate.format(formatter);
    }

    private static void setProperties(Properties props, Properties propertyRead) {
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", propertyRead.getProperty("ssl.factory"));
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
    }

    private static Properties readPropertyFile() throws IOException {
        Properties property = new Properties();
        property.load(LanguageManager.class
                .getClassLoader()
                .getResourceAsStream("mail/mail.properties"));
        return property;
    }

    private static void setUpMailSend(Properties propertiesRead, String login, String content, String subject) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = System.getProperties();
        setProperties(props, propertiesRead);
        Session session = Session.getInstance(props, null);
        final MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(propertiesRead.getProperty("send.from") + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(login, false));
        msg.setSubject(subject);
        msg.setContent(content, "text/html; charset=utf-8");

        SMTPTransport t = (SMTPTransport) session.getTransport(propertiesRead.getProperty("protocol"));

        t.connect("smtp.gmail.com", propertiesRead.getProperty("send.from"), propertiesRead.getProperty("password"));
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }
}