package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.language.LanguageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SubmitKey extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(SubmitKey.class);
    private static final String LOGIN = "login";
    private static final String MAGIC_KEY = "key";
    private static final String APP_LOCALE = "appLocale";
    private static final String INCORRECT_KEY = "incorrectKey";
    private static final String TRUE = "TRUE";


    private UserService userService;
    private LanguageManager languageManager;

    public SubmitKey() {
        this.userService = new UserServiceImpl();
        this.languageManager =  LanguageManager.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(LOGIN);
        String key = req.getParameter(MAGIC_KEY);
        String language = String.valueOf(req.getParameter(APP_LOCALE));

        languageManager.setLanguage(language);

        /*Optional<String> userKeyOptional = userService.findMagicKey(login);
        if(userKeyOptional.isPresent()) {
            String userKey = userKeyOptional.get();
            if(userKey.equals(key)) {
                userService.changeSubmitKeyStatus(login);
            }
            else {
                LOGGER.warn("Someone put in incorrect key.");
                req.getSession().setAttribute(INCORRECT_KEY, TRUE);
            }
            return CommandResult.forward(SUBMIT_KEY);
        }*/
        String userKey = userService.findMagicKey(login);
        if(userKey.equals(key)) {
            userService.changeSubmitKeyStatus(login);
        }
        else {
            LOGGER.warn("Someone put in incorrect key.");
            req.getSession().setAttribute(INCORRECT_KEY, TRUE);
        }
        return CommandResult.forward(SUBMIT_KEY);

        //return CommandResult.forward(ERROR_PAGE);
    }

}
