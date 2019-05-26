package controller.command;

import controller.pages.Pages;
import model.entity.TestInfo;
import model.entity.User;
import model.service.impl.TestInfoService;
import uitility.mail.MailsSender;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SendResults extends Command implements Pages {
    private static final String USER_ID = "test_user_id";

    private TestInfoService testInfoService;

    public SendResults() {
        this.testInfoService = new TestInfoService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: when user that alredy registrated - optional amd classcastexception
        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getUserId();

        List<TestInfo> userTests = testInfoService.findTestsByParameter(USER_ID, userId);

        TestInfo testInfo = userTests.get(userTests.size() - 1);

        MailsSender.send(testInfo);

        return new CommandResult(START_PAGE);
    }

}
