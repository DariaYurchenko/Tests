package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartTest extends Command implements CommandPages {
    private static final String THEME = "theme_id";
    private static final String COUNTER = "counter";
    private static final String QUESTIONS_LIST = "questions";
    private static final String ANSWERS_LIST = "userAnswers";

    private QuestionServiceImpl questionServiceImpl;

    public StartTest() {
        this.questionServiceImpl = new QuestionServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String themeId = req.getParameter(THEME);
        List<Question> questions = questionServiceImpl.findQuestionsByTheme(Long.parseLong(themeId));
        List<Answer> answers = new ArrayList<>();
        Collections.shuffle(questions);

        req.getSession().setAttribute(QUESTIONS_LIST, questions);

        int counter = Integer.parseInt(req.getParameter(COUNTER));

        req.getSession().setAttribute(COUNTER, counter);
        req.getSession().setAttribute(ANSWERS_LIST, answers);
        req.getSession().setAttribute(THEME, themeId);

        return CommandResult.forward(new PassTest());
    }
}
