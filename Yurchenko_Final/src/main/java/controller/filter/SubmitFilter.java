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


@WebFilter(filterName = "SubmitFilter")
public class SubmitFilter implements Filter, CommandPages {
    private static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";
    private static final String LOGIN = "LOGIN";
    private static final String REGISTER = "REGISTER";
    private static final String SUBMIT_KEY = "SUBMIT_KEY";
    private static final String SEND_EMAIL_AGAIN = "SEND_EMAIL_AGAIN";
    private static final String LOG_OUT = "LOGOUT";

    private static final String  LOGIN_URL = "http://localhost:8081/login_page.jsp";
    private static final String  START_PAGE_URL = "http://localhost:8081/";
    private static final String  REGISTER_PAGE_URL = "http://localhost:8081/register_page.jsp";
    private static final String  TESTS_TO_PASS_URL = "http://localhost:8081/tests_to_pass.jsp";
    private static final String  NOT_SUBMIT_EMAIL_URL = "http://localhost:8081/not_submit_email.jsp";
    private static final String  NOT_SUBMIT_EMAIL2_URL = "http://localhost:8081/tests/not_submit_email.jsp";

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

        /*String login = null;
        if(session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            login = user.getLogin();
        }

        Integer submitStatus = null;
        Optional<Integer> submitOptional = userService.findIfSubmit(login);
        if(submitOptional.isPresent()) {
            submitStatus = submitOptional.get();
        }*/
        String url = req.getRequestURL().toString();
        Integer ifSubmit = 0;

        Optional<User> optionalUser = Optional.ofNullable((User)session.getAttribute("user"));
        if(optionalUser.isPresent()) {
            String userLogin = optionalUser.get().getLogin();
            ifSubmit = userService.findIfSubmit(userLogin).orElse(0);
        }

        if(ifSubmit == SUBMITTED_USER || isDoFilterCommands(req) || isDoFilterForPage(url)) {
           filterChain.doFilter(req, resp);
        }
        else {
           resp.sendRedirect(NOT_SUBMIT_EMAIL);
        }

        //String url = req.getRequestURL().toString();

        /*if(submitStatus != null && submitStatus == SUBMITTED_USER || isDoFilterCommands(req) || isDoFilterForPage(url)) {
            filterChain.doFilter(req, resp);
            resp.sendRedirect(NOT_SUBMIT_EMAIL);
        }
        else {
            resp.sendRedirect(NOT_SUBMIT_EMAIL);
        }*/

    }

    private boolean isDoFilterCommands(HttpServletRequest req) {
        String command = req.getParameter("command");
        return CHANGE_LANGUAGE.equals(command) || LOGIN.equals(command) || REGISTER.equals(command) ||
                SUBMIT_KEY.equals(command) || SEND_EMAIL_AGAIN.equals(command) || LOG_OUT.equals(command);
    }

    private boolean isDoFilterForPage(String url) {
        List<String> pagesNotToFilter = new ArrayList<>(Arrays.asList(NOT_SUBMIT_EMAIL2_URL, REGISTER_PAGE_URL, LOGIN_URL, TESTS_TO_PASS_URL, START_PAGE_URL,
                NOT_SUBMIT_EMAIL_URL));
        return pagesNotToFilter.contains(url);
    }

    @Override
    public void destroy() {

    }
}