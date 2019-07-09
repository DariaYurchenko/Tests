package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import uitility.mail.MailsSender;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Called when user didn't submit registration and
 * want to send email with submitting link again.
 */
public class SendEmailAgain extends Command implements CommandPages {
    private UserService userService;

    public SendEmailAgain() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String language = (String) req.getSession().getAttribute("appLocale");
        String login = user.getLogin();

        Optional<String> optKey = userService.findMagicKey(login);

        if(optKey.isPresent()) {
            String magicKey = optKey.get();

            sendEmailToConfirmRegistrationAgain(login, language, magicKey);

            req.getSession().setAttribute("sent", "TRUE");
            return CommandResult.forward(NOT_SUBMIT_EMAIL);
        }

        return CommandResult.forward(ERROR_404_PAGE);
    }

    private void sendEmailToConfirmRegistrationAgain(String login, String language, String magicKey) {
        MailsSender.sendEmailToConfirmRegistration(login, magicKey, language);
    }

}
