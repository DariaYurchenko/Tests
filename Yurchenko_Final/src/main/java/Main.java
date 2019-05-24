import model.dao.CourseDao;
import model.dao.QuestionDao;
import model.dao.connector.Connector;
import model.dao.impl.CourseDaoImpl;
import model.dao.impl.QuestionDaoImpl;
import model.entity.Question;
import model.entity.entityenum.QuestionType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
        QuestionDao questionDao = new QuestionDaoImpl(connector);
        Question question = new Question.Builder()
                .withCourseId(1L)
                .withQuestion("public class Main {\n" +
                        "    public static void main(String args[]) {\n" +
                        "        byte b = 0;\n" +
                        "        while (++b > 0);\n" +
                        "        System.out.println(b);\n" +
                        "    }\n" +
                        "}")
                .withQuestionType(new QuestionType("text"))
                .withIncorrectOption1("12")
                .withIncorrectOption2("345")
                .withIncorrectOption3("32")
                .withCorrectOption1("123")
                .build();
        //questionDao.create(question);
        questionDao.update("incorrect_option1", "DSA", 3L);
        //questionDao.deleteById(2L);
        //questionDao.changeAmountOfAnswers(3L, 23, 45);
        List<Question> list = questionDao.findAll();
        System.out.println(list);
        //System.out.println(questionDao.findById(6L));
        //questionDao.getCurrentAmountOfAnswers(3L);

        CourseDao courseDao = new CourseDaoImpl(connector);
        courseDao.findAll();





    }
}
