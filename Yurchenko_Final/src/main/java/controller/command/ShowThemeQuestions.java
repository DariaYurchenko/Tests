package controller.command;

import controller.pages.Pages;
import model.entity.Question;
import model.service.impl.QuestionService;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowThemeQuestions extends Command implements Pages  {
    private  QuestionService questionService = new QuestionService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        Long themeId = Long.parseLong(req.getParameter("radio"));
        List<Question> questionList = questionService.findQuestionsForPaginationId(pagination.calculateStart(),
                recordsPerPage, themeId);

        int rows = questionService.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("questions", questionList);
        req.setAttribute("act", "SHOW_BY_THEME_ID");

        return CommandResult.forward("show_questions.jsp");


    }
}
