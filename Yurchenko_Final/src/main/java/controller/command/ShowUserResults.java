package controller.command;

import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.service.impl.TestInfoServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserResults extends Command implements CommandPages {
    private TestInfoServiceImpl testInfoServiceImpl = new TestInfoServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.parseLong(req.getParameter("radio"));
        int currentPage = 1;

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        List<TestInfo> testInfoList = testInfoServiceImpl.findUserTestsForPagination(userId, pagination.calculateStart(),
                recordsPerPage);

        int rows = testInfoServiceImpl.findTestsByParameter("test_user_id", userId).size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("showTests", "The list of tests: ");
        req.setAttribute("testInfoList", testInfoList);

        return CommandResult.forward(SHOW_USER_TESTS);


    }
}
