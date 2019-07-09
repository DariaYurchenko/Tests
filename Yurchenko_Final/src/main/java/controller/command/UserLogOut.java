package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invalidate session and log out user.
 */
public class UserLogOut extends Command implements CommandPages {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return CommandResult.forward(LOGIN_PAGE);
    }
}
