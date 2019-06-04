package controller.filter;

import model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "testsPassed")
public class TestPassedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User user = (User) req.getSession().getAttribute("user");
        List<Long> themesId = (List<Long>) req.getSession().getAttribute("userThemes");

        if(user != null && !themesId.isEmpty() && "START_TEST".equals(req.getParameter("command"))) {
            for(Long themeId : themesId) {
                if(themeId.toString().equals(req.getParameter("theme_id"))) {
                    req.getRequestDispatcher("jsp/tests_to_pass.jsp").forward(req, resp);
                }
            }
        }
        else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
