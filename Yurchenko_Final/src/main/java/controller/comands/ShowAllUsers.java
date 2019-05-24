package controller.comands;

import controller.pages.Pages;
import model.entity.User;
import model.service.impl.UserService;
import org.apache.log4j.Logger;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllUsers extends Command implements Pages {
    private static final Logger logger = Logger.getLogger(UserLogin.class);

    private UserService userService;

    public ShowAllUsers(UserService userService) {
        this.userService = userService;
    }

    public ShowAllUsers() {
        this.userService = new UserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentPage = Integer.valueOf(req.getParameter("currentPage"));
        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        List<User> users = userService.findUsersForPagination(pagination.calculateStart(), recordsPerPage);
        if (users == null) {
            logger.warn("No users in database.");
            return CommandResult.forward(ERROR_PAGE);
        }

        int rows = userService.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("showUsers", "The list of users: ");
        req.setAttribute("users", users);
        return CommandResult.forward("show_users.jsp");
    }
}
