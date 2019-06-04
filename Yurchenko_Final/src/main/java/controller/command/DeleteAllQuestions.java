package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.QuestionService;
import model.service.factory.ServiceFactory;
import model.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllQuestions extends Command {
    private QuestionService questionService;

    public DeleteAllQuestions(QuestionService questionService) {
        this.questionService = questionService;
    }

    public DeleteAllQuestions() {
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        questionService.deleteAll();
        return CommandResult.forward(new ShowAllQuestions());
    }
}
