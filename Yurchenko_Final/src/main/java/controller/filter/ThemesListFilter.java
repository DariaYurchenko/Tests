package controller.filter;

import model.entity.Test;
import model.entity.User;
import model.service.TestService;
import model.service.factory.ServiceFactory;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Filter forms list of themes user has passed while testing.
 */
@WebFilter(filterName = "themesListFilter")
public class ThemesListFilter implements Filter {
    private static final String TESTS = "tests_to_pass";

    private TestService testService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.testService = ServiceFactory.getInstance().getTestService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (TESTS.equals(req.getParameter("page"))) {
            Optional<User> userOpt = Optional.ofNullable((User) req.getSession().getAttribute("user"));

            if (userOpt.isPresent()) {
                List<Test> tests = testService.findTestsByParameter("test_user_id", userOpt.get().getUserId());
                List<Integer> themesId = tests.stream()
                        .map(Test::getThemeId)
                        .collect(Collectors.toList());
                req.getSession().setAttribute("userThemes", themesId);
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
