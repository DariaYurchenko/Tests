package controller.command;

import controller.pages.CommandPages;
import model.entity.User;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllUsers extends Command implements CommandPages {
    private static final Logger LOGGER = Logger.getLogger(UserLogin.class);

    private UserServiceImpl userServiceImpl;

    public ShowAllUsers(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public ShowAllUsers() {
        this.userServiceImpl = new UserServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

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


        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        int rows = userServiceImpl.findAll().size();
        List<User> users = userServiceImpl.findUsersForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)), recordsPerPage);


        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("showUsers", "The list of users: ");
        req.getSession().setAttribute("users", users);
        return CommandResult.forward(SHOW_USERS);
    }
}
