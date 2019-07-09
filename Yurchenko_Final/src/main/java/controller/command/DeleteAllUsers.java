package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Let admin delete all users.
 */
public class DeleteAllUsers extends Command implements CommandPages {
    private UserService userService;

    public DeleteAllUsers() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        userService.deleteAllUsers();
        return CommandResult.forward(new AdministrateUsers());
    }
}
