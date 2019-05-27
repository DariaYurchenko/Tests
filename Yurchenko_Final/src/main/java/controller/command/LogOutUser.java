package controller.command;

import controller.pages.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutUser extends Command implements Pages {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
