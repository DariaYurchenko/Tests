package controller.command;

import controller.pages.Pages;
import model.entity.Answer;
import model.entity.Test;
import model.entity.User;
import model.entity.entityenum.AnswerStatus;
import model.entity.entityenum.TestStatus;
import model.service.impl.TestService;
import model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowResults extends Command implements Pages {
    private UserService userService;
    private TestService testService;

    public ShowResults() {
        this.userService = new UserService();
        this.testService = new TestService();
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

        /*Optional<User> optUser = (Optional<User>) req.getSession().getAttribute("user");
        User user = null;
        if(optUser.isPresent()) {
            user = optUser.get();
        }*/

        User user = (User) req.getSession().getAttribute("user");

        userService.setRank(user.getUserId(), userPoints, maxPoints);

        Long themeId = Long.parseLong(String.valueOf(req.getSession().getAttribute("theme_id")));

        Test test = new Test.Builder()
                .withUserId(user.getUserId())
                .withThemeId(themeId)
                .withTestStatus(setStatus(percent))
                .withUserPoints(userPoints)
                .withMaxPoints(maxPoints)
                .withRightAnswersPercent(percent)
                .withDate(LocalDate.now())
                .build();
        testService.addTestToDatabase(test);

        if(percent >= 50) {
            req.getSession().setAttribute("passed", "TRUE");
        }

        req.getSession().setAttribute("userPoints", userPoints);
        req.getSession().setAttribute("maxPoints", maxPoints);
        req.getSession().setAttribute("percent", percent);
        //req.setAttribute("user", userAnswers.size());


        return CommandResult.forward(SHOW_RESULTS);
    }

    private TestStatus setStatus(double percent) {
        return (percent >= 50) ? TestStatus.PASSED : TestStatus.NOT_PASSED;
    }
}
