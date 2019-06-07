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
import java.util.List;

/**
 * Filter blocks tests which have been already passed by user
 */
@WebFilter(filterName = "testPassedFilter")
public class TestPassedFilter implements Filter {
    private static final String START_TEST = "START_TEST";
    private static final String TESTS = "jsp/tests_to_pass.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if (START_TEST.equals(req.getParameter("command"))) {
            List<Integer> themesId = (ArrayList) session.getAttribute("userThemes");

            if(themesId.contains(Integer.parseInt(req.getParameter("theme_id")))) {
                req.getRequestDispatcher(TESTS).forward(req, resp);
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
