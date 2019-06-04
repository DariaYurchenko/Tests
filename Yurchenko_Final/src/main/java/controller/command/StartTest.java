package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.service.QuestionService;
import model.service.factory.ServiceFactory;
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

    private QuestionService questionService;

    public StartTest() {
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String themeId = req.getParameter(THEME);
        List<Question> questions = questionService.findQuestionsByTheme(Long.parseLong(themeId));

        List<Answer> answers = new ArrayList<>();

        Collections.shuffle(questions);

        List<Question> questionsRus = new ArrayList<>();
        for(Question question : questions) {
            Long id = question.getQuestionId();
            questionsRus.add(questionService.getRus(Long.parseLong(themeId), id));
        }

        req.getSession().setAttribute(QUESTIONS_LIST, questions);
        req.getSession().setAttribute("rusQuestions", questionsRus);
        req.getSession().setAttribute("forward", null);

        int counter = Integer.parseInt(req.getParameter(COUNTER));

        req.getSession().setAttribute(COUNTER, counter);
        req.getSession().setAttribute(ANSWERS_LIST, answers);
        req.getSession().setAttribute(THEME, themeId);

        return CommandResult.forward(new PassTest());
    }
}
