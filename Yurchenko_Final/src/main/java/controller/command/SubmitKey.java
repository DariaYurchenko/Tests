package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Called automatically after user follows the link
 * with registration submit in email.
 */
public class SubmitKey extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(SubmitKey.class);

    private UserService userService;

    public SubmitKey() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String key = req.getParameter("key");

        Optional<String> optKey = userService.findMagicKey(login);

        if(optKey.isPresent() && optKey.get().equals(key)) {
            userService.changeSubmitKeyStatus(login);
        }
        else {
            LOGGER.warn("Someone put in incorrect key.");
            req.getSession().setAttribute("incorrectKey", "TRUE");
        }
        return CommandResult.forward(SUBMIT_KEY);
    }

}
