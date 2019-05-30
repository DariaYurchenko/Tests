package controller.command;

import controller.pages.CommandPages;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitKey extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(SubmitKey.class);

    private UserService userService;
    private LanguageManager languageManager;

    public SubmitKey() {
        this.userService = new UserServiceImpl();
        this.languageManager =  LanguageManager.INSTANCE;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String key = req.getParameter("key");
        String language = String.valueOf(req.getParameter("appLocale"));

        languageManager.setLanguage(language);

        String userKey = userService.findMagicKey(login);
        if(userKey.equals(key)) {
            userService.changeSubmitKeyStatus(login);
        }
        else {
            LOGGER.warn("Someone put in incorrect key.");
            req.getSession().setAttribute("incorrectKey", "TRUE");
        }
        return CommandResult.forward(SUBMIT_KEY);
    }


}
