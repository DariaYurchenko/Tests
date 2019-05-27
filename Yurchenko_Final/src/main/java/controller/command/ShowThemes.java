package controller.command;

import controller.pages.Pages;
import model.entity.Theme;
import model.service.impl.ThemeService;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowThemes extends Command implements Pages {
    private ThemeService themeService = new ThemeService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        List<Theme> themes = themeService.findThemesForPagination(pagination.calculateStart(), recordsPerPage);
        //List<Theme> themes = themeService.findAll();

        int rows = themeService.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("themes", themes);
        req.setAttribute("act", "SHOW_BY_THEME");

        return CommandResult.forward("show_questions.jsp");
    }
}
