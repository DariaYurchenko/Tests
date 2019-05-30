package controller.command;

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
    private QuestionServiceImpl questionServiceImpl;

    public StartTest() {
        this.questionServiceImpl = new QuestionServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String themeId = req.getParameter("theme_id");
        List<Question> questions = questionServiceImpl.findQuestionsByTheme(Long.parseLong(themeId));
        //List<Answer> answers = new ArrayList<>();
        Collections.shuffle(questions);

        req.getSession().setAttribute("questions", questions);

        int counter = Integer.parseInt(req.getParameter("counter"));

        req.getSession().setAttribute("counter", counter);
        //req.getSession().setAttribute("userAnswers", answers);
        req.getSession().setAttribute("theme_id", themeId);

        return CommandResult.forward(new PassTest());
    }
}
