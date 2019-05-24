package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguage extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //CHANGED!!!
        /*String language = (String) req.getSession().getAttribute("lang");
        String address = (String) req.getSession().getAttribute("address");*/
        String language = req.getParameter("lang");
        String address = req.getParameter("address");

        if(language.equalsIgnoreCase("eng")) {
            req.setAttribute("appLocale", "en_UK");
            return CommandResult.forward(address);
        }
        else {
            req.setAttribute("appLocale", "ru_RU");
            return CommandResult.forward(address);
        }
    }
}
