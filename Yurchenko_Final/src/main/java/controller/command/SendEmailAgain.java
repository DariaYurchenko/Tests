package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import model.service.impl.UserServiceImpl;
import uitility.mail.MailsSender;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailAgain extends Command implements CommandPages {
    private UserService userService;

    public SendEmailAgain() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String language = (String) req.getSession().getAttribute("appLocale");
        String login = user.getLogin();

        sendEmailToConfirmRegistrationAgain(login, language);
        req.setAttribute("sent", "TRUE");

        return CommandResult.forward(NOT_SUBMIT_EMAIL);
    }

    private void sendEmailToConfirmRegistrationAgain(String login, String language) {
        String magicKey = userService.findMagicKey(login);
        MailsSender.sendEmailToConfirmRegistration(login, magicKey, language);
    }
}
