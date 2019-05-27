package uitility.mail;

import model.dao.TestDao;
import model.dao.connector.Connector;
import model.dao.impl.TestDaoImpl;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;
import model.entity.Theme;
import model.service.impl.ThemeService;
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


        ThemeService themeService = new ThemeService();
        List<Theme> themes = themeService.findThemesForPagination(1, 5);
        System.out.println(themes);












    }





    }

