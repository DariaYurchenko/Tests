package uitility.mail;

import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;
import org.apache.commons.io.FileUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        /*Connector connector = new Connector();
        QuestionDao questionDao = new QuestionDaoImpl(connector);
        Question question = new Question.Builder()
                .withQuestion("1")
                .withQuestionType(new QuestionType("radio"))
                .withTheme(new Theme("operators"))
                .withIncorrectOption1("12")
                .withIncorrectOption2("345")
                .withIncorrectOption3("32")
                .withCorrectOption1("123")
                .build();
        QuestionService questionService = new QuestionService();*/
        Connector connector = new Connector();
        TestInfoDaoImpl testInfoDao = new TestInfoDaoImpl(connector);
        List<TestInfo> list = testInfoDao.findByParameter("test_id", 2L);
        TestInfo testInfo = list.get(0);

     File file = new File("src\\main\\resources\\userMail");
        FileReader reader = new FileReader(file);
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        LocalDate localDate = testInfo.getDate();

        System.out.println(content);
         String ready = String.format(content, testInfo.getUserName() + " " + testInfo.getUserLastName(), testInfo.getTheme(),
                testInfo.getUserPoints().toString(), testInfo.getMaxPoints().toString(), testInfo.getRightAnswersPercent().toString(), "passed", localDate.format(formatter),
                testInfo.getUserRank());
        System.out.println(ready);

        final Properties properties = new Properties();
     properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));

     Session session = Session.getDefaultInstance(properties);
     MimeMessage message = new MimeMessage(session);
     message.setFrom(new InternetAddress("yurchenkod2017"));
     message.addRecipient(Message.RecipientType.TO, new InternetAddress("yurchenkod95@gmail.com"));

     message.setSubject("Hi");
     message.setContent(ready, "text/html; charset=utf-8");

     Transport transport = session.getTransport();
     transport.connect(null, "230da68sha19");
     //transport.sendMessage(message, message.getAllRecipients());
     transport.close();

    // MailSender.sendEmail(testInfo);
    /*MailsSender.Send(testInfo, "yurchenkod2017", "230da68sha19", "yurchenkod95@gmail.com",
            "", "HJDS", "fsdfs");*/







    }





    }

