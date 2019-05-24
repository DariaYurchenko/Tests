package controller.comands;

import controller.pages.Pages;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguage extends Command implements Pages {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
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
