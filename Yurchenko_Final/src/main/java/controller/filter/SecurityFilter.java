package controller.filter;

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

/**
 * Filter tracks user's status and let admin
 * have additional activity
 */
@WebFilter(filterName = "securityFilter")
public class SecurityFilter implements Filter {
    private static final List<String> PAGES_TO_SKIP = new ArrayList<>();
    private static final List<String> COMMANDS_TO_SKIP = new ArrayList<>();

    private static final String LOGIN_PAGE_REDIRECT = "jsp/login_page.jsp";

    private static final String ADMIN = "Admin";

    private static final String SHOW_ALL_USERS = "SHOW_ALL_USERS";
    private static final String DELETE_USER_BY_ID = "DELETE_USER_BY_ID";
    private static final String DELETE_ALL_USERS = "DELETE_ALL_USERS";
    private static final String SHOW_USER_RESULTS = "SHOW_USER_RESULTS";
    private static final String REGISTER_ADMIN = "REGISTER_ADMIN";
    private static final String SHOW_ALL_QUESTIONS = "SHOW_ALL_QUESTIONS";
    private static final String SHOW_QUESTIONS_BY_THEME = "SHOW_QUESTIONS_BY_THEME";
    private static final String SHOW_THEME_QUESTIONS = "SHOW_THEME_QUESTIONS";

    private static final String ADMIN_PAGE = "admin_page";
    private static final String SHOW_QUESTIONS_PAGE = "show_questions";
    private static final String SHOW_USERS_PAGE = "show_users";
    private static final String THEMES = "themes";
    private static final String USERS_TESTS = "user_tests";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if(isFilter(req, session)) {
            filterChain.doFilter(req, resp);
        }
        else {
            req.getRequestDispatcher(LOGIN_PAGE_REDIRECT).forward(req, resp);
        }
    }

    private boolean isFilter(HttpServletRequest req, HttpSession session) {
        return session.getAttribute("user") != null && ADMIN.equals(session.getAttribute("userStatus")) ||
                isDoFilterForCommand(req) || !isDoFilterForPage(req);
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        COMMANDS_TO_SKIP.addAll(Arrays.asList(SHOW_ALL_QUESTIONS, SHOW_ALL_USERS,
                SHOW_QUESTIONS_BY_THEME, SHOW_THEME_QUESTIONS, SHOW_USER_RESULTS, DELETE_ALL_USERS, DELETE_USER_BY_ID,
                REGISTER_ADMIN));
        return COMMANDS_TO_SKIP.contains(command);
    }

    private boolean isDoFilterForPage(HttpServletRequest req) {
        String page = req.getParameter("page");
        PAGES_TO_SKIP.addAll(Arrays.asList(ADMIN_PAGE, SHOW_USERS_PAGE, SHOW_QUESTIONS_PAGE,
                THEMES, USERS_TESTS));

        return PAGES_TO_SKIP.contains(page);

    }

    @Override
    public void destroy() {

    }
}
