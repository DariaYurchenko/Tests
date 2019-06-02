package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllQuestions extends Command implements CommandPages {
    private QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        questionServiceImpl.deleteAll();
        return CommandResult.forward(new ShowAllQuestions());
    }
}
