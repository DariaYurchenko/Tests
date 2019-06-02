package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllUsers extends Command implements CommandPages {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        userServiceImpl.deleteAllUsers();
        return CommandResult.forward(new ShowAllUsers());
    }
}
