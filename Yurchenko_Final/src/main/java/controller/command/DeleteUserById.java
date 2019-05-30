package controller.command;

import controller.pages.CommandPages;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserById  extends Command implements CommandPages {

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("radio"));
        userServiceImpl.deleteUserById(userId);

        return CommandResult.forward(new ShowAllUsers());



    }
}
