package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.service.impl.UserServiceImpl;
import uitility.mail.MailsSender;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailAgain extends Command implements CommandPages {
    private UserServiceImpl userServiceImpl;

    public SendEmailAgain() {
        this.userServiceImpl = new UserServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();

        sendEmailToConfirmRegistrationAgain(login);
        req.setAttribute("sent", "TRUE");

        return CommandResult.forward(NOT_SUBMIT_EMAIL);
    }

    private void sendEmailToConfirmRegistrationAgain(String login) {
        String magicKey = userServiceImpl.findMagicKey(login);
        MailsSender.sendEmailToConfirmRegistration(login, magicKey);
    }
}
