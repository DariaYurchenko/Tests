package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.User;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class ShowAllUsers extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);

    private UserService userService;

    public ShowAllUsers() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String requestCurrentPage = req.getParameter("currentPage");
        Integer currentPage;
        //Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        if(requestCurrentPage == null) {
            currentPage = 1;
        }else {
            currentPage = Integer.parseInt(requestCurrentPage);
        }

        String act = req.getParameter("act");
        if("DELETE_USER_BY_ID".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_USER_BY_ID");
        }
        if("JUST_SHOW".equals(act)) {
            req.getSession().setAttribute("act", "JUST_SHOW");
        }
        if("DELETE_ALL_USERS".equals(act)) {
            req.getSession().setAttribute("act", "DELETE_ALL_USERS");
        }
        if("SHOW_USER_RESULTS".equals(act)) {
            req.getSession().setAttribute("act", "SHOW_USER_RESULTS");
        }
        if("REGISTER_ADMIN".equals(act)) {
            req.getSession().setAttribute("act", "REGISTER_ADMIN");
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

        int rows = userService.findAll().size();
        List<User> users = userService.findUsersForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)), recordsPerPage);
        int usersSize = users.size();

        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("showUsers", "The list of users: ");
        req.getSession().setAttribute("users", users);
        req.getSession().setAttribute("usersSize", usersSize);
        return CommandResult.forward(SHOW_USERS);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userService);
    }
}
