package controller.command;

import controller.pages.CommandPages;
import model.entity.Question;
import model.service.impl.QuestionServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllQuestions extends Command implements CommandPages {
    private QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();

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

        List<Question> questions = questionServiceImpl.findQuestionsForPagination(pagination.calculateStart(), recordsPerPage);

        int rows = questionServiceImpl.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("questions", questions);
        return CommandResult.forward(SHOW_QUESTIONS);
    }
}
