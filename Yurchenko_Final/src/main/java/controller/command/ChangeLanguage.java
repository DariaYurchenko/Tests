package controller.command;

import controller.command.result.CommandResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Called to change language in the session.
 */
public class ChangeLanguage extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("lang");
        String address = req.getParameter("address");

        if("eng".equalsIgnoreCase(language)) {
            req.getSession().setAttribute("appLocale", "en_UK");
        }
        else {
            req.getSession().setAttribute("appLocale", "ru_RU");
        }

        return CommandResult.forward(address);
    }

}
