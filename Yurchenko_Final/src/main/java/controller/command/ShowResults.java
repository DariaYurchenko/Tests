package controller.command;

import controller.pages.Pages;
import model.entity.Answer;
import model.entity.User;
import model.entity.entityenum.AnswerStatus;
import model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowResults extends Command implements Pages {
    private UserService userService;

    public ShowResults() {
        this.userService = new UserService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Answer> userAnswers = (ArrayList) req.getSession().getAttribute("userAnswers");
        int userPoints =  userAnswers.stream()
                .filter(answer -> answer.getAnswerStatus().equals(AnswerStatus.CORRECT))
                .mapToInt(Answer::getMaxPoints)
                .sum();
        int maxPoints = userAnswers.stream()
                .mapToInt(Answer::getMaxPoints)
                .sum();
        double percent = Math.round((userPoints * 1.0 / maxPoints) * 100) / 1.0;

        User user = (User) req.getSession().getAttribute("user");
        userService.setRank(user.getUserId(), userPoints, maxPoints);

        if(percent >= 50) {
            req.getSession().setAttribute("passed", "TRUE");
        }

        req.getSession().setAttribute("userPoints", userPoints);
        req.getSession().setAttribute("maxPoints", maxPoints);
        req.getSession().setAttribute("percent", percent);
        //req.setAttribute("user", userAnswers.size());

        return CommandResult.forward(SHOW_RESULTS);
    }
}
