package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.service.impl.TestInfoServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ShowUserResults extends Command implements CommandPages {
    private TestInfoServiceImpl testInfoServiceImpl = new TestInfoServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Optional<String> optId = Optional.ofNullable(req.getParameter("radio"));
        Long userId = null;
        if(optId.isPresent()) {
            userId = Long.parseLong(req.getParameter("radio"));
        }
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

        if(userId != null) {
        int rows = testInfoServiceImpl.findTestsByParameter("test_user_id", userId).size();
        List<TestInfo> testInfoList = testInfoServiceImpl.findUserTestsForPagination(userId, pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);
        int testsSize = testInfoList.size();
            req.getSession().setAttribute("start", pagination.calculateStart(pagination.calculateNumOfPages(rows)));
            req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
            req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
            req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
            req.getSession().setAttribute("showTests", "The list of tests: ");
            req.getSession().setAttribute("testInfoList", testInfoList);
            req.getSession().setAttribute("testsSize", testsSize);
            return CommandResult.forward(SHOW_USER_TESTS);
        }



       /* int currentPage = 1;

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);*/

        /*//TODO: paginator counts itself
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

        int rows = testInfoServiceImpl.findTestsByParameter("test_user_id", userId).size();
        List<TestInfo> testInfoList = testInfoServiceImpl.findUserTestsForPagination(userId, pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);

        req.getSession().setAttribute("start", pagination.calculateStart(pagination.calculateNumOfPages(rows)));
        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("showTests", "The list of tests: ");
        req.getSession().setAttribute("testInfoList", testInfoList);*/


        return CommandResult.forward(SHOW_USER_TESTS);


    }
}
