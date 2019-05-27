package controller.filter;

import model.dao.UserDao;
import model.dao.connector.Connector;
import model.dao.impl.UserDaoImpl;
import model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "submitFilter")
public class SubmitFilter implements Filter {
    UserDao userDao = new UserDaoImpl(new Connector());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String login = null;
        if(session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            login = user.getLogin();
        }
        Integer submit = userDao.getSubmit(login);
        req.getSession().setAttribute("su", submit);
        //int submit = 2;



        if(submit == 2 || "LOGIN".equals(req.getParameter("command")) ||
                "REGISTER".equals(req.getParameter("command")) || "SUBMIT_KEY".equals(req.getParameter("command"))
        || "SEND_EMAIL_AGAIN".equals(req.getParameter("command"))) {
            filterChain.doFilter(req, resp);
        }
        else {
            resp.sendRedirect("not_submit_email.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}