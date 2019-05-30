package controller.command;

import controller.pages.CommandPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutUser extends Command implements CommandPages {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return CommandResult.redirect(LOGIN_PAGE);
    }
}
