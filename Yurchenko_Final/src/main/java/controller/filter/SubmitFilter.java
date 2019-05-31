package controller.filter;

import controller.pages.CommandPages;
import model.entity.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebFilter(filterName = "submit")
public class SubmitFilter implements Filter {
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
        this.userService = new UserServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String page = req.getParameter("page");

        Integer ifSubmit = 0;

        Optional<User> optionalUser = Optional.ofNullable((User)session.getAttribute("user"));
        if(optionalUser.isPresent()) {
            String userLogin = optionalUser.get().getLogin();
            ifSubmit = userService.findIfSubmit(userLogin).orElse(0);
        }

        if(ifSubmit == SUBMITTED_USER || isDoFilterForCommand(req) || isDoFilterForPage(page)) {
           filterChain.doFilter(req, resp);
        }
        else {
            req.getRequestDispatcher(NOT_SUBMIT_EMAIL_REDIRECT).forward(req, resp);
        }
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(REGISTER, LOGIN, CHANGE_PASSWORD,
                CHANGE_LANGUAGE, SEND_EMAIL_AGAIN, SUBMIT_KEY, LOGOUT, PASS_TESTS));
        return pagesNotToFilter.contains(command);
    }

    private boolean isDoFilterForPage(String page) {
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(NOT_SUBMIT_EMAIL_PAGE, REGISTER_PAGE, LOGIN_PAGE,
                TESTS_TO_PASS_PAGE, START_PAGE, ERROR_PAGE));
        return pagesNotToFilter.contains(page);
    }

    @Override
    public void destroy() {

    }
}