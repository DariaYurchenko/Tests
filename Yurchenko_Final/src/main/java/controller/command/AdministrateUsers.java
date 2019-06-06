package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import uitility.pagination.Pagination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Is for choosing operation with list of users on admin page.
 */
public class AdministrateUsers extends Command implements CommandPages {
    private static final String DELETE_USER_BY_ID = "DELETE_USER_BY_ID";
    private static final String JUST_SHOW = "JUST_SHOW";
    private static final String DELETE_ALL_USERS = "DELETE_ALL_USERS";
    private static final String SHOW_USER_RESULTS = "SHOW_USER_RESULTS";
    private static final String REGISTER_ADMIN = "REGISTER_ADMIN";

    private UserService userService;

    public AdministrateUsers() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = setCurrentPage(req);
        int recordsPerPage = setRecordsPerPage(req);
        int rows = setRows();

        setActionForSession(req);

        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        List<User> users = findUsers(pagination, recordsPerPage, rows);
        int usersSize = users.size();

        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("showUsers", "The list of users: ");
        req.getSession().setAttribute("users", users);
        req.getSession().setAttribute("usersSize", usersSize);
        return CommandResult.forward(ADMIN_USERS);
    }

    private int setCurrentPage(HttpServletRequest req) {
        String requestCurrentPage = req.getParameter("currentPage");
        return requestCurrentPage == null ? 1 : Integer.parseInt(requestCurrentPage);
    }

    private void setActionForSession(HttpServletRequest req) {
        String act = req.getParameter("act");
        if(DELETE_USER_BY_ID.equals(act)) {
            req.getSession().setAttribute("act", DELETE_USER_BY_ID);
        }
        if(JUST_SHOW.equals(act)) {
            req.getSession().setAttribute("act", JUST_SHOW);
        }
        if(DELETE_ALL_USERS.equals(act)) {
            req.getSession().setAttribute("act", DELETE_ALL_USERS);
        }
        if(SHOW_USER_RESULTS.equals(act)) {
            req.getSession().setAttribute("act", SHOW_USER_RESULTS);
        }
        if(REGISTER_ADMIN.equals(act)) {
            req.getSession().setAttribute("act", REGISTER_ADMIN);
        }
    }

    private int setRecordsPerPage(HttpServletRequest req) {
        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        return requestRecordsPerPage == null ? 5 : Integer.parseInt(requestRecordsPerPage);
    }

    private int setRows() {
        return userService.findAllUsers().size();
    }

    private List<User> findUsers(Pagination pagination, int recordsPerPage, int rows) {
        return userService.findUsersForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);
    }

}
