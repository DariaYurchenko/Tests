package uitility.mail;

import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.dao.impl.QuestionDaoImpl;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.*;
import model.service.TestService;
import model.service.ThemeService;
import model.service.impl.QuestionServiceImpl;
import model.service.impl.TestServiceImpl;
import model.service.impl.ThemeServiceImpl;
import uitility.pagination.Pagination;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        /*String NAME_OR_LASTNAME = "^[a-zA-Zа-яА-ЯёЁ]{1,30}$";
        User user = new User.Builder().withId(168L).build();
        TestService testService = new TestServiceImpl();
        List<Test> tests = testService.findTestsInfoByParameter("test_user_id", user.getUserId());
        System.out.println(tests);
        //Test test1 = testService.findTestById(1L);
        //System.out.println(test1);
        *//*Test test = testService.findTestById(1L);
        System.out.println(test);*/

        QuestionDao questionDao = DaoFactory.getDAOFactory(DbNames.MYSQL).getQuestionDao();
        Question question = questionDao.findTranslatedThemeQuestion(1L, 1L).get();
        System.out.println(question);

        //MailsSender.sendEmailToConfirmRegistration("yurchenkod95@gmail.com", "fsdfsdsdf", "ru_RU");
    }

}
