package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import model.service.QuestionService;
import model.service.factory.ServiceFactory;
import model.service.impl.QuestionServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllQuestions extends Command implements CommandPages {
    private QuestionService questionService;

    public ShowAllQuestions() {
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        String requestCurrentPage = req.getParameter("currentPage");
        Integer currentPage;
        //Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        if(requestCurrentPage == null) {
            currentPage = 1;
        }else {
            currentPage = Integer.parseInt(requestCurrentPage);
        }

        String act = req.getParameter("act");
        if("SHOW_BY_THEME".equals(act)) {
            req.getSession().setAttribute("act", "SHOW_BY_THEME");
        }
        if("JUST_SHOW".equals(act)) {
            req.getSession().setAttribute("act", "JUST_SHOW");
        }
        /*if("DELETE_ALL_QUESTIONS".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_ALL_QUESTIONS");
        }
        if("DELETE_QUESTION_BY_ID".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_QUESTION_BY_ID");
        }
        if("REGISTER_ADMIN".equals(act)) {
            req.getSession().setAttribute("act", "REGISTER_ADMIN");
        }*/


        //int recordsPerPage = 5;
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

        int rows = questionService.findAll().size();
        List<Question> questions = questionService.findQuestionsForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)), recordsPerPage);
        int questionsSize = questions.size();

        req.getSession().setAttribute("start", pagination.calculateStart(pagination.calculateStart(pagination.calculateNumOfPages(rows))));
        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("questions", questions);
        req.getSession().setAttribute("questionsSize", questionsSize);
        return CommandResult.forward(SHOW_QUESTIONS);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) return true;
        else return false;
    }

}
