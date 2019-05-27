package controller.command;

import controller.pages.Pages;
import model.service.impl.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllUsers extends Command implements Pages {
    private UserService userService = new UserService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        userService.deleteAllUsers();
        return CommandResult.forward(new ShowAllUsers());
    }
}
