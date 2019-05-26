package uitility.mail;

import model.entity.TestInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class MailSender {
    private static final Logger LOGGER = Logger.getLogger(MailSender.class);

    private static File file = new File("src\\main\\resources\\userMail");

    public static void sendEmail(TestInfo testInfo) {
        try {
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            final String user = "yurchenkod2017@gmail.com";
            final String password = "230da68sha19";

            final Properties props = new Properties();
            props.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));


            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yurchenkod2017@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yurchenkod95@gmail.com"));

            message.setSubject("Test passing results");
            //message.setContent(makeMessage(content, testInfo), "text/html; charset=utf-8");
            message.setText("HI");

            Transport transport = session.getTransport();
            transport.connect(null, "230da68sha19");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();



        } catch (IOException e) {
            LOGGER.error("Can't send an email, IOException: " + e.getMessage());
        } catch (AddressException e) {
            LOGGER.error("Can't send an email, AddressException: " + e.getMessage());
        } catch (MessagingException e) {
            LOGGER.error("Can't send an email, MessagingException: " + e.getMessage());
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
}


