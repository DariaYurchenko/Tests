package controller.filter;

import model.entity.User;
import model.service.UserService;
import model.service.factory.ServiceFactory;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Filter blocks some activity of users
 * who didn't submit registration by email
 */
@WebFilter(filterName = "submitFilter")
public class SubmitFilter implements Filter {
    private static final List<String> PAGES_TO_SKIP = new ArrayList<>();
    private static final List<String> COMMANDS_TO_SKIP = new ArrayList<>();

    private static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";
    private static final String LOGIN = "LOGIN";
    private static final String REGISTER = "REGISTER";
    private static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    private static final String SUBMIT_KEY = "SUBMIT_KEY";
    private static final String SEND_EMAIL_AGAIN = "SEND_EMAIL_AGAIN";
    private static final String LOGOUT = "LOGOUT";
    private static final String PASS_TESTS = "PASS_TESTS";

    private static final String LOGIN_PAGE = "login_page";
    private static final String START_PAGE = "start_page";
    private static final String TESTS_TO_PASS_PAGE = "tests_to_pass";
    private static final String REGISTER_PAGE = "register_page";
    private static final String NOT_SUBMIT_EMAIL_PAGE = "not_submit_email";
    private static final String ERROR_PAGE = "error_page";

    private static final String NOT_SUBMIT_EMAIL_REDIRECT = "jsp/not_submit_email.jsp";

    private static final int SUBMITTED_USER = 2;

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String page = req.getParameter("page");

        /**
         * user's submit email status in db
         */
        int ifSubmit = 0;

        Optional<User> optionalUser = Optional.ofNullable((User)session.getAttribute("user"));
        if(optionalUser.isPresent()) {
            String userLogin = optionalUser.get().getLogin();
            ifSubmit = userService.findIfSubmit(userLogin).orElse(0);
        }

        if(isFilter(ifSubmit, page, req)) {
           filterChain.doFilter(req, resp);
        }
        else {
            req.getRequestDispatcher(NOT_SUBMIT_EMAIL_REDIRECT).forward(req, resp);
        }
    }

    private boolean isFilter(int ifSubmit, String page, HttpServletRequest req) {
        return ifSubmit == SUBMITTED_USER || isDoFilterForCommand(req) || isDoFilterForPage(page);
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        COMMANDS_TO_SKIP.addAll(Arrays.asList(REGISTER, LOGIN, CHANGE_PASSWORD,
                CHANGE_LANGUAGE, SEND_EMAIL_AGAIN, SUBMIT_KEY, LOGOUT, PASS_TESTS));
        return COMMANDS_TO_SKIP.contains(command);
    }

    private boolean isDoFilterForPage(String page) {
        PAGES_TO_SKIP.addAll(Arrays.asList(NOT_SUBMIT_EMAIL_PAGE, REGISTER_PAGE, LOGIN_PAGE,
                TESTS_TO_PASS_PAGE, START_PAGE, ERROR_PAGE));
        return PAGES_TO_SKIP.contains(page);
    }

    @Override
    public void destroy() {

    }
}
