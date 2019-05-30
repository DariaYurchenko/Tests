package controller.command;

import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Test;
import model.entity.User;
import model.entity.status.AnswerStatus;
import model.entity.status.TestStatus;
import model.service.impl.TestServiceImpl;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShowResults extends Command implements CommandPages {
    private UserServiceImpl userServiceImpl;
    private TestServiceImpl testServiceImpl;

    public ShowResults() {
        this.userServiceImpl = new UserServiceImpl();
        this.testServiceImpl = new TestServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Answer> userAnswers = (ArrayList) req.getSession().getAttribute("userAnswers");

        int userPoints = countAmountOfRightUserAnswers(userAnswers);
        int maxPoints = countAmountOfAllUserAnswers(userAnswers);
        double percent = countPercentOfUserRightAnswers(userAnswers);

        User user = (User) req.getSession().getAttribute("user");
        changeUserRankInDb(user, userPoints, maxPoints);

        saveTestInDb(req, user, userPoints, maxPoints, percent);

        if(percent >= 50) {
            req.getSession().setAttribute("passed", "TRUE");
        }

        req.getSession().setAttribute("userPoints", userPoints);
        req.getSession().setAttribute("maxPoints", maxPoints);
        req.getSession().setAttribute("percent", percent);

        return CommandResult.forward(SHOW_RESULTS);
    }

    private Double countPercentOfUserRightAnswers(List<Answer> userAnswers) {
        int userPoints =  countAmountOfRightUserAnswers(userAnswers);
        int maxPoints = countAmountOfAllUserAnswers(userAnswers);
        return Math.round((userPoints * 1.0 / maxPoints) * 100) / 1.0;
    }

    private int countAmountOfRightUserAnswers(List<Answer> userAnswers) {
        return userAnswers.stream()
                .filter(answer -> answer.getAnswerStatus().equals(AnswerStatus.CORRECT))
                .mapToInt(Answer::getMaxPoints)
                .sum();
    }

    private int countAmountOfAllUserAnswers(List<Answer> userAnswers) {
        return userAnswers.stream()
                .mapToInt(Answer::getMaxPoints)
                .sum();
    }

    private void changeUserRankInDb(User user, int userPoints, int maxPoints) {
        userServiceImpl.setRank(user.getLogin(), userPoints, maxPoints);
    }

    private void saveTestInDb(HttpServletRequest req, User user, int userPoints, int maxPoints, double percentOfRightAnswers) {
        Long themeId = Long.parseLong(String.valueOf(req.getSession().getAttribute("theme_id")));

        Test test = new Test.Builder()
                .withUserId(user.getUserId())
                .withThemeId(themeId)
                .withTestStatus(setStatus(percentOfRightAnswers))
                .withUserPoints(userPoints)
                .withMaxPoints(maxPoints)
                .withRightAnswersPercent(percentOfRightAnswers)
                .withDate(LocalDate.now())
                .build();
        testServiceImpl.addTestToDatabase(test);
    }

    private TestStatus setStatus(double percent) {
        return (percent >= 50) ? TestStatus.PASSED : TestStatus.NOT_PASSED;
    }
}
