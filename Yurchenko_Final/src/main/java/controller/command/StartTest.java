package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.service.QuestionService;
import model.service.impl.QuestionServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Called before passing test. Forms list of questions on chosen
 * theme and shuffle it.
 */
public class StartTest extends Command implements CommandPages {
    private QuestionService questionService;

    public StartTest() {
        this.questionService = new QuestionServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Integer themeId = Integer.parseInt(Optional.ofNullable(req.getParameter("theme_id")).orElse("0"));

        List<Question> questions = questionService.findQuestionsByTheme(themeId);

        Collections.shuffle(questions);

        List<Question> questionsRus = makeCopyOfQuestionListInRussian(questions, themeId);

        List<Answer> userAnswers = new ArrayList<>();

        req.getSession().setAttribute("questions", questions);
        req.getSession().setAttribute("rusQuestions", questionsRus);
        req.getSession().setAttribute("userAnswers", userAnswers);
        req.getSession().setAttribute("forward", "SHOW_QUESTION");

        int counter = Integer.parseInt(req.getParameter("counter"));

        req.getSession().setAttribute("counter", counter);
        req.getSession().setAttribute("theme_id", themeId);

        return CommandResult.forward(new ShowQuestion());
    }

    /**
     * makes copy of shuffled list in russian
     * in order to let user switch languages during test
     * with saving all parameters and states including
     * the order of questions
     */
    private List<Question> makeCopyOfQuestionListInRussian(List<Question> questions, Integer themeId) {
        return questions.stream()
                .map(Question::getQuestionId)
                .map(questionId -> questionService.findTranslatedQuestion(themeId, questionId))
                .map(optQuestion -> optQuestion.orElse(new Question.Builder().build()))
                .collect(Collectors.toList());
    }

}
