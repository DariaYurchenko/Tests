package controller.command;

import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.entity.User;
import model.service.impl.TestInfoServiceImpl;
import uitility.mail.MailsSender;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SendResults extends Command implements CommandPages {
    private static final String USER_ID = "test_user_id";

    private TestInfoServiceImpl testInfoServiceImpl;

    public SendResults() {
        this.testInfoServiceImpl = new TestInfoServiceImpl();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: when user that alredy registrated - optional amd classcastexception
        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getUserId();

        List<TestInfo> userTests = testInfoServiceImpl.findTestsByParameter(USER_ID, userId);

        TestInfo testInfo = userTests.get(userTests.size() - 1);

        req.getSession().setAttribute("sent", "TRUE");

        MailsSender.sendTestResults(testInfo);

        return CommandResult.forward(SHOW_RESULTS);
    }

}
