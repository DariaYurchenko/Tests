package controller.filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);
    private static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";
    private static final String LOGIN = "LOGIN";
    private static final String REGISTER = "REGISTER";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String command = req.getParameter("command");

        if(session != null && session.getAttribute("user")!= null || isDoFilter(req)) {
            filterChain.doFilter(req, resp);
        }
        else {
            resp.sendRedirect(req.getContextPath()+"/");
        }
    }

    private boolean isDoFilter(HttpServletRequest req) {

        String command = req.getParameter("command");
        return CHANGE_LANGUAGE.equals(command) || LOGIN.equals(command) || REGISTER.equals(command);
    }

    @Override
    public void destroy() {

    }
}
