package controller.command;

import controller.pages.Pages;
import model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserById  extends Command implements Pages {

    private UserService userService = new UserService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("radio"));
        userService.deleteUserById(userId);

        return CommandResult.forward(new ShowAllUsers());



    }
}
