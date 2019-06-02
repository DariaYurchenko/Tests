package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Theme;
import model.service.impl.ThemeServiceImpl;
import uitility.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowThemes extends Command implements CommandPages {
    private ThemeServiceImpl themeServiceImpl = new ThemeServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);*/
        String requestCurrentPage = req.getParameter("currentPage");
        Integer currentPage;
        if(requestCurrentPage == null) {
            currentPage = 1;
        }else {
            currentPage = Integer.parseInt(requestCurrentPage);
        }

        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        Integer recordsPerPage;
        if(requestRecordsPerPage == null) {
            recordsPerPage = 5;
        }
        else {
            recordsPerPage = Integer.parseInt(requestRecordsPerPage);
        }
        req.getSession().setAttribute("recordsPerPage", recordsPerPage);
        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        int rows = themeServiceImpl.findAll().size();
        List<Theme> themes = themeServiceImpl.findThemesForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)), recordsPerPage);
        int themesSize = themes.size();

        req.getSession().setAttribute("start", pagination.calculateStart(pagination.calculateNumOfPages(rows)));
        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("themes", themes);
        req.getSession().setAttribute("themesSize", themesSize);
        req.getSession().setAttribute("act", "SHOW_BY_THEME");

        return CommandResult.forward(SHOW_QUESTIONS);
    }
}
