package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.entity.User;
import model.service.TestInfoService;
import model.service.factory.ServiceFactory;
import model.service.impl.TestInfoServiceImpl;
import uitility.mail.MailsSender;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SendResults extends Command implements CommandPages {
    private static final String USER_ID = "test_user_id";

    private TestInfoService testInfoService;

    public SendResults() {
        this.testInfoService = ServiceFactory.getInstance().getTestInfoService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO: when user that alredy registrated - optional amd classcastexception
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();
        String language = (String) req.getSession().getAttribute("appLocale");

        List<TestInfo> userTests = testInfoService.findTestsByParameter("login", login);

        TestInfo testInfo = userTests.get(userTests.size() - 1);

        req.getSession().setAttribute("sent", "TRUE");

        MailsSender.sendTestResults(testInfo, language);

        return CommandResult.forward(SHOW_RESULTS);
    }

}
