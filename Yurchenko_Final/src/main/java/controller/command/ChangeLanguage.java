package controller.command;

import controller.command.result.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguage extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("lang");
        String address = req.getParameter("address");

        if(language.equalsIgnoreCase("eng")) {
            req.getSession().setAttribute("appLocale", "en_UK");
        }
        else {
            req.getSession().setAttribute("appLocale", "ru_RU");
        }
        return CommandResult.forward(address);
    }
}
