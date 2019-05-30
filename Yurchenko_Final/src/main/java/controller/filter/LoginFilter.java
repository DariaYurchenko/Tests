package controller.filter;

import controller.pages.CommandPages;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter, CommandPages {
    private static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";
    private static final String LOGIN = "LOGIN";
    private static final String REGISTER = "REGISTER";
    private static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    private static final String SUBMIT_KEY = "SUBMIT_KEY";

    private static final String  LOGIN_URL = "http://localhost:8081/login_page.jsp";
    private static final String  START_PAGE_URL = "http://localhost:8081/start_page.jsp";
    private static final String  START_LOCALHOST_URL = "http://localhost:8081/";
    private static final String  TESTS_TO_PASS_URL = "http://localhost:8081/tests_to_pass.jsp";
    private static final String  REGISTER_PAGE_URL = "http://localhost:8081/register_page.jsp";
    private static final String  NOT_SUBMIT_EMAIL_URL = "http://localhost:8081/not_submit_email.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();

        if(session != null && session.getAttribute("user")!= null || isDoFilterForCommand(req) || isDoFilterForPage(req, url)) {
            filterChain.doFilter(req, resp);
        }
        else {
            resp.sendRedirect(LOGIN_PAGE);
        }
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        return CHANGE_LANGUAGE.equals(command) || LOGIN.equals(command) || REGISTER.equals(command) ||
                CHANGE_PASSWORD.equals(command) || SUBMIT_KEY.equals(command);
    }

    private boolean isDoFilterForPage(HttpServletRequest req, String url) {
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(NOT_SUBMIT_EMAIL_URL, START_LOCALHOST_URL,
                REGISTER_PAGE_URL, LOGIN_URL, TESTS_TO_PASS_URL, START_PAGE_URL));
        return pagesNotToFilter.contains(url);

    }



    @Override
    public void destroy() {

    }
}
