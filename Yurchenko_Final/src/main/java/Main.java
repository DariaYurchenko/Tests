import model.dao.ThemeDao;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.dao.impl.ThemeDaoImpl;
import model.dao.impl.QuestionDaoImpl;
import model.entity.Question;
import model.entity.Theme;
import model.entity.entityenum.QuestionType;
import model.service.impl.QuestionService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
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
        QuestionService questionService = new QuestionService();
        // questionDao.create(question);
        //questionDao.update("incorrect_option1", "DS", 5L);
        //questionDao.deleteById(5L);
        //questionDao.changeAmountOfAnswers(3L, 23, 45);
        Question question1 = questionDao.findById(6L).get();
        List<Question> questions = questionService.findQuestionsByTheme(Long.parseLong("1"));
        System.out.println(questions);
        //System.out.println(list);
        //System.out.println(questionDao.findById(6L));
        //questionDao.getCurrentAmountOfAnswers(3L);

       // ThemeDao themeDao = new ThemeDaoImpl(connector);
        //themeDao.findAll();





    }
}
