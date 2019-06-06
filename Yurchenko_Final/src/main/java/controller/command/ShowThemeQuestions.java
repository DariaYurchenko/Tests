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
import java.util.Optional;

/**
 * Show list of questions on chosen theme in admin page.
 */
public class ShowThemeQuestions extends Command implements CommandPages {
    private QuestionService questionService;

    public ShowThemeQuestions() {
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = setCurrentPage(req);
        int recordsPerPage = setRecordsPerPage(req);
        int rows = setRows();

        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        Optional<String> optId = Optional.ofNullable(req.getParameter("radio"));

        if(optId.isPresent()) {
            Integer themeId = Integer.parseInt(req.getParameter("radio"));

            List<Question> questionList = findThemeQuestions(pagination, recordsPerPage, rows, themeId);
            int questionsSize = questionList.size();

            req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
            req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
            req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
            req.getSession().setAttribute("questions", questionList);
            req.getSession().setAttribute("questionsSize", questionsSize);
            req.getSession().setAttribute("act", "SHOW_BY_THEME_ID");
        }
        return CommandResult.forward(ADMIN_QUESTIONS);
    }

    private int setCurrentPage(HttpServletRequest req) {
        String requestCurrentPage = req.getParameter("currentPage");
        return requestCurrentPage == null ? 1 : Integer.parseInt(requestCurrentPage);
    }

    private int setRecordsPerPage(HttpServletRequest req) {
        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        return requestRecordsPerPage == null ? 5 : Integer.parseInt(requestRecordsPerPage);
    }

    private int setRows() {
        return questionService.findAllQuestions().size();
    }

    private List<Question> findThemeQuestions(Pagination pagination, int recordsPerPage, int rows, Integer themeId) {
        return questionService.findThemeQuestionsForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage, themeId);
    }

}
