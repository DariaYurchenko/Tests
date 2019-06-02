package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import model.service.impl.QuestionServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ShowThemeQuestions extends Command implements CommandPages {
    private QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);*/
        String requestCurrentPage = req.getParameter("currentPage");
        Integer currentPage;
        if(requestCurrentPage == null) {
            currentPage = 1;
        }else {
            currentPage = Integer.parseInt(requestCurrentPage);
        }

        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        Integer recordsPerPage;
        if(requestRecordsPerPage == null) {
            recordsPerPage = 5;
        }
        else {
            recordsPerPage = Integer.parseInt(requestRecordsPerPage);
        }
        req.getSession().setAttribute("recordsPerPage", recordsPerPage);
        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        int rows = questionServiceImpl.findAll().size();

        Optional<String> optId = Optional.ofNullable(req.getParameter("radio"));
        if(optId.isPresent()) {
            Long themeId = Long.parseLong(req.getParameter("radio"));
            List<Question> questionList = questionServiceImpl.findQuestionsForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                    recordsPerPage, themeId);
            int questionsSize = questionList.size();

            req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
            req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
            req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
            req.getSession().setAttribute("questions", questionList);
            req.getSession().setAttribute("questionsSize", questionsSize);
            req.getSession().setAttribute("act", "SHOW_BY_THEME_ID");
           //return CommandResult.forward(new ShowAllQuestions());
            return CommandResult.forward(SHOW_QUESTIONS);
        }
       /* Long themeId = Long.parseLong(req.getParameter("radio"));
        List<Question> questionList = questionServiceImpl.findQuestionsForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage, themeId);

        req.getSession().setAttribute("start", pagination.calculateStart(pagination.calculateNumOfPages(rows)));
        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("questions", questionList);
        req.getSession().setAttribute("act", "SHOW_BY_THEME_ID");*/

        return CommandResult.forward(SHOW_QUESTIONS);


    }
}
