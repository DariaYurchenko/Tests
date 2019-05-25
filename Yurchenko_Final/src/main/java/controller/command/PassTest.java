package controller.command;

import controller.pages.Pages;
import model.entity.Question;
import model.service.impl.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PassTest extends Command implements Pages {
    private QuestionService questionService;

    public PassTest() {
        this.questionService = new QuestionService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");

        if(req.getParameter("forward") == null || req.getParameter("forward").equals("FALSE")) {
            //List<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");

            int counter = Integer.parseInt(String.valueOf(req.getSession().getAttribute("counter")));

            Question question = questions.get(counter);
            List<String> options = new ArrayList<>(Arrays.asList(question.getIncorrectOption1(),
                    question.getIncorrectOption2(), question.getIncorrectOption3(), question.getCorrectOption1()));
            Collections.shuffle(options);

            req.getSession().setAttribute("options", options);
            req.getSession().setAttribute("question", question);
            req.getSession().setAttribute("length", questions.size());
            /*req.getSession().setAttribute("forward", "TRUE");
            return CommandResult.forward(LOGIN_PAGE);*/
        }
        else {
            String userAnswer = req.getParameter("radio");
            Question question = (Question) req.getSession().getAttribute("question");

            if(userAnswer.equals(question.getCorrectOption1())) {
                questionService.setAnswers(question.getQuestionId(), 1, 1);
            }
            else {
                questionService.setAnswers(question.getQuestionId(), 0, 1);
            }
            Question question1 = questionService.findById(question.getQuestionId());
            String answerPercent = String.valueOf(question1.getPercentOfRightAnswers());


            List<String> options = (ArrayList<String>) req.getSession().getAttribute("options");
            req.getSession().setAttribute("options", options);
            req.getSession().setAttribute("question", question);
            req.getSession().setAttribute("userAnswer", userAnswer);
            req.setAttribute("answerPercent", answerPercent);
            int counter = Integer.parseInt(req.getParameter("counter"));
            req.setAttribute("forward", "FALSE");
            req.getSession().setAttribute("counter", counter + 1);
            req.getSession().setAttribute("length", questions.size());
        }



        return CommandResult.forward(PASS_TESTS);
    }
}
