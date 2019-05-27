package controller.command;

import controller.pages.Pages;
import model.entity.Question;
import model.entity.User;
import model.service.impl.QuestionService;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllQuestions extends Command implements Pages {
    private QuestionService questionService = new QuestionService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        String act = req.getParameter("act");
        if("SHOW_BY_THEME".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_USER_BY_ID");
        }
        if("JUST_SHOW".equals(act)) {
            req.getSession().setAttribute("act", "JUST_SHOW");
        }
        if("DELETE_ALL_QUESTIONS".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_ALL_QUESTIONS");
        }
        if("DELETE_QUESTION_BY_ID".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_QUESTION_BY_ID");
        }
        if("REGISTER_ADMIN".equals(act)) {
            req.getSession().setAttribute("act", "REGISTER_ADMIN");
        }


        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        List<Question> questions = questionService.findQuestionsForPagination(pagination.calculateStart(), recordsPerPage);

        int rows = questionService.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("questions", questions);
        return CommandResult.forward("show_questions.jsp");
    }
}
