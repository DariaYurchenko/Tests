package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.TestInfo;
import model.service.TestInfoService;
import model.service.factory.ServiceFactory;
import uitility.pagination.Pagination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Show information about all tests passed by chosen user in admin page.
 */
public class ShowUserResults extends Command implements CommandPages {
    private TestInfoService testInfoService;

    public ShowUserResults() {
        this.testInfoService = ServiceFactory.getInstance().getTestInfoService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = setCurrentPage(req);
        int recordsPerPage = setRecordsPerPage(req);

        Optional<String> optId = Optional.ofNullable(req.getParameter("radio"));
        if(optId.isPresent()) {
            Long userId = Long.parseLong(req.getParameter("radio"));

            int rows = setRows(userId);

            Pagination pagination = new Pagination(recordsPerPage, currentPage);

            List<TestInfo> testInfoList = findTestInfos(pagination, recordsPerPage, rows, userId);
            int testsSize = testInfoList.size();

            req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
            req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
            req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
            req.getSession().setAttribute("showTests", "The list of tests: ");
            req.getSession().setAttribute("testInfoList", testInfoList);
            req.getSession().setAttribute("testsSize", testsSize);
            req.getSession().setAttribute("userId", userId);
            return CommandResult.forward(ADMIN_USER_TESTS);

        }

        return CommandResult.forward(ADMIN_USERS);
    }

    private int setCurrentPage(HttpServletRequest req) {
        String requestCurrentPage = req.getParameter("currentPage");
        return requestCurrentPage == null ? 1 : Integer.parseInt(requestCurrentPage);
    }

    private int setRecordsPerPage(HttpServletRequest req) {
        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        return requestRecordsPerPage == null ? 5 : Integer.parseInt(requestRecordsPerPage);
    }

    private int setRows(Long userId) {
        return testInfoService.findTestsInfoByParameter("test_user_id", userId).size();
    }

    private List<TestInfo> findTestInfos(Pagination pagination, int recordsPerPage, int rows, Long userId) {
        return testInfoService.findUserTestInfoForPagination(userId, pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);
    }

}
