package controller.command;

import controller.pages.Pages;
import model.entity.Question;
import model.service.impl.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StartTest extends Command implements Pages {
    private QuestionService questionService;

    public StartTest() {
        this.questionService = new QuestionService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String themeId = req.getParameter("theme_id");
        int counter = Integer.parseInt(req.getParameter("counter"));
        List<Question> questions = questionService.findQuestionsByTheme(Long.parseLong(themeId));

        Question question = questions.get(counter);
        req.getSession().setAttribute("question", question);
        req.getSession().setAttribute("counter", counter+1);
        req.getSession().setAttribute("length", questions.size());

        return CommandResult.forward(START_TESTS);
    }
}
