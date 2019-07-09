package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Let admin delete user by id.
 */
public class DeleteUserById  extends Command implements CommandPages {

    private UserService userService;

    public DeleteUserById() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Optional<String> optId = Optional.ofNullable(req.getParameter("radio"));

        if(optId.isPresent()) {
            Integer userId = Integer.parseInt(req.getParameter("radio"));
            userService.deleteUserById(userId);
        }

        return CommandResult.forward(new AdministrateUsers());
    }

}
