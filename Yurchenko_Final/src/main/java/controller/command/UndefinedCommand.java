package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UndefinedCommand extends Command implements CommandPages {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return CommandResult.forward(ERROR_404_PAGE);
    }
}
