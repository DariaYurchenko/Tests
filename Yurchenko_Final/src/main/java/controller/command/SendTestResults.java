package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.entity.User;
import model.service.TestInfoService;
import model.service.impl.TestInfoServiceImpl;
import uitility.mail.MailsSender;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Called when user agrees to send more detailed information
 * about test which was already passed via email.
 */
public class SendTestResults extends Command implements CommandPages {
    private TestInfoService testInfoService;

    public SendTestResults() {
        this.testInfoService = new TestInfoServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();
        String language = (String) req.getSession().getAttribute("appLocale");

        List<TestInfo> userTests = testInfoService.findTestsInfoByParameter("login", login);

        TestInfo testInfo = userTests.get(userTests.size() - 1);

        req.getSession().setAttribute("sent", "TRUE");

        MailsSender.sendTestResults(testInfo, language);

        return CommandResult.forward(SHOW_RESULTS);
    }

}
