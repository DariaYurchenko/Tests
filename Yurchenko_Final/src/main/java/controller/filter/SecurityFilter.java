package controller.filter;

import controller.pages.CommandPages;
import model.entity.User;
import model.entity.status.UserStatus;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter, CommandPages {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if(session.getAttribute("user") != null && session.getAttribute("userStatus") == "Admin") {
            filterChain.doFilter(req, resp);
        }
        else {
            resp.sendRedirect(LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
}
