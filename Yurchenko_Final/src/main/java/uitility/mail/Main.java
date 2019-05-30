package uitility.mail;

import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.TestInfo;
import model.entity.Theme;
import model.service.impl.ThemeServiceImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

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
        QuestionServiceImpl questionService = new QuestionServiceImpl();*/
        Connector connector = new Connector();
        TestInfoDaoImpl testInfoDao = new TestInfoDaoImpl(connector);
        List<TestInfo> list = testInfoDao.findByParameter("test_id", 2L);


        ThemeServiceImpl themeServiceImpl = new ThemeServiceImpl();
        List<Theme> themes = themeServiceImpl.findThemesForPagination(1, 5);
        System.out.println(themes);












    }





    }

