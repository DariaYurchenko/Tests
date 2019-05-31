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

@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {
    private static final String LOGIN_PAGE_REDIRECT = "jsp/login_page.jsp";

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
        String page = req.getParameter("page");

        if(session.getAttribute("user") != null && session.getAttribute("userStatus") == "Admin" ||
                isDoFilterForCommand(req) || !isDoFilterForPage(page)) {
            filterChain.doFilter(req, resp);
        }
        else {
            resp.sendRedirect(LOGIN_PAGE_REDIRECT);
        }
    }

    private boolean isDoFilterForCommand(HttpServletRequest req) {
        String command = req.getParameter("command");
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(SHOW_ALL_QUESTIONS, SHOW_ALL_USERS,
                SHOW_QUESTIONS_BY_THEME, SHOW_THEME_QUESTIONS, SHOW_USER_RESULTS, DELETE_ALL_USERS, DELETE_USER_BY_ID,
                REGISTER_ADMIN));
        return pagesNotToFilter.contains(command);
    }

    private boolean isDoFilterForPage(String page) {
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(ADMIN_PAGE, SHOW_USERS_PAGE, SHOW_QUESTIONS_PAGE,
                THEMES, USERS_TESTS));
        return pagesNotToFilter.contains(page);

    }

    @Override
    public void destroy() {

    }
}
