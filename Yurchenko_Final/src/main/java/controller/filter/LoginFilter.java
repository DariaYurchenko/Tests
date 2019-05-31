package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "login")
public class LoginFilter implements Filter {
    private static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";
    private static final String LOGIN = "LOGIN";
    private static final String REGISTER = "REGISTER";
    private static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    private static final String SUBMIT_KEY = "SUBMIT_KEY";
    private static final String SEND_EMAIL_AGAIN = "SEND_EMAIL_AGAIN";
    private static final String LOGOUT = "LOGOUT";

    private static final String LOGIN_PAGE = "login_page";
    private static final String START_PAGE = "start_page";
    private static final String TESTS_TO_PASS_PAGE = "tests_to_pass";
    private static final String REGISTER_PAGE = "register_page";
    private static final String ERROR_PAGE = "error_page";
    private static final String NOT_SUBMIT_EMAIL_PAGE = "not_submit_email";
    private static final String LOGIN_PAGE_REDIRECT = "jsp/login_page.jsp";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String page = req.getParameter("page");

        if(session.getAttribute("user")!= null || isDoFilterForCommand(req) || isDoFilterForPage(page)) {
            filterChain.doFilter(req, resp);
        }

        else {
            req.getRequestDispatcher(LOGIN_PAGE_REDIRECT).forward(req, resp);
        }
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(REGISTER, LOGIN, CHANGE_PASSWORD,
                CHANGE_LANGUAGE, SEND_EMAIL_AGAIN, SUBMIT_KEY, LOGOUT));
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
