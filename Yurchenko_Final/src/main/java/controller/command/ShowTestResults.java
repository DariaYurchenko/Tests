package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Test;
import model.entity.User;
import model.entity.status.AnswerStatus;
import model.entity.status.TestStatus;
import model.service.TestService;
import model.service.UserService;
import model.service.impl.TestServiceImpl;
import model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Called after passing of test. Count user's points on the base of his answers and
 * put them in db. Save current test in db. Show user's results and test's status (passed or not).
 * Proposes to send more detailed information on current test via email.
 */
public class ShowTestResults extends Command implements CommandPages {
    private UserService userService;
    private TestService testService;

    public ShowTestResults() {
        this.userService = new UserServiceImpl();
        this.testService = new TestServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Answer> userAnswers = (ArrayList) req.getSession().getAttribute("userAnswers");

        int userPoints = countAmountOfRightUserAnswers(userAnswers);
        int maxPoints = countAmountOfAllUserAnswers(userAnswers);
        double percent = countPercentOfUserRightAnswers(userAnswers);

        User user = (User) req.getSession().getAttribute("user");
        changeUserRankInDb(user, userPoints, maxPoints);

        saveTest(req, user, userPoints, maxPoints, percent);

        isPassedTest(req, percent);

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
                .reduce(0, Integer::sum);
    }

    private int countAmountOfAllUserAnswers(List<Answer> userAnswers) {
        return userAnswers.stream()
                .mapToInt(Answer::getMaxPoints)
                .reduce(0, Integer::sum);
    }

    private void changeUserRankInDb(User user, int userPoints, int maxPoints) {
        userService.setRank(user.getLogin(), userPoints, maxPoints);
    }

    private void saveTest(HttpServletRequest req, User user, int userPoints, int maxPoints, double percentOfRightAnswers) {
        Integer themeId = Integer.parseInt(String.valueOf(req.getSession().getAttribute("theme_id")));

        Test test = new Test.Builder()
                .withUserId(user.getUserId())
                .withThemeId(themeId)
                .withTestStatus(setStatus(percentOfRightAnswers))
                .withUserPoints(userPoints)
                .withMaxPoints(maxPoints)
                .withRightAnswersPercent(percentOfRightAnswers)
                .withDate(LocalDate.now())
                .build();
        testService.addTest(test);
    }

    private void isPassedTest(HttpServletRequest req, double percent) {
        if(percent >= 50) {
            req.getSession().setAttribute("passed", "TRUE");
        }
        else {
            req.getSession().setAttribute("passed", null);
        }
    }

    private TestStatus setStatus(double percent) {
        return (percent >= 50) ? TestStatus.PASSED : TestStatus.NOT_PASSED;
    }
}
