package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import model.service.QuestionService;
import model.service.factory.ServiceFactory;
import uitility.pagination.Pagination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Is for choosing operation with list of questions on admin page.
 */
public class AdministrateQuestions extends Command implements CommandPages {
    private static final String SHOW_BY_THEME = "SHOW_BY_THEME";
    private static final String JUST_SHOW = "JUST_SHOW";

    private QuestionService questionService;

    public AdministrateQuestions() {
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = setCurrentPage(req);
        int recordsPerPage = setRecordsPerPage(req);
        int rows = setRows();

        setActionForSession(req);

        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        List<Question> questions = findQuestions(pagination, recordsPerPage, rows);
        int questionsSize = questions.size();

        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("questions", questions);
        req.getSession().setAttribute("questionsSize", questionsSize);
        return CommandResult.forward(ADMIN_QUESTIONS);
    }

    private int setCurrentPage(HttpServletRequest req) {
        String requestCurrentPage = req.getParameter("currentPage");
        return requestCurrentPage == null ? 1 : Integer.parseInt(requestCurrentPage);
    }

    private void setActionForSession(HttpServletRequest req) {
        String act = req.getParameter("act");

        if(SHOW_BY_THEME.equals(act)) {
            req.getSession().setAttribute("act", SHOW_BY_THEME);
        }
        if(JUST_SHOW.equals(act)) {
            req.getSession().setAttribute("act", JUST_SHOW);
        }
    }

    private int setRecordsPerPage(HttpServletRequest req) {
        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        return requestRecordsPerPage == null ? 5 : Integer.parseInt(requestRecordsPerPage);
    }

    private int setRows() {
        return questionService.findAllQuestions().size();
    }

    private List<Question> findQuestions(Pagination pagination, int recordsPerPage, int rows) {
        return questionService.findQuestionsForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);
    }

}
