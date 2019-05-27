package controller.command;

import controller.pages.Pages;
import model.service.impl.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionById extends Command implements Pages {
    private QuestionService questionService = new QuestionService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long questionId = Long.parseLong(req.getParameter("radio"));
        questionService.deleteById(questionId);

        return CommandResult.forward(new ShowAllQuestions());
    }
}
