package controller.command;

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
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        int recordsPerPage = 5;
        Pagination pagination = new Pagination(5, currentPage);

        List<Theme> themes = themeServiceImpl.findThemesForPagination(pagination.calculateStart(), recordsPerPage);
        //List<Theme> themes = themeServiceImpl.findAll();

        int rows = themeServiceImpl.findAll().size();
        req.setAttribute("start", pagination.calculateStart());
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("themes", themes);
        req.setAttribute("act", "SHOW_BY_THEME");

        return CommandResult.forward("show_questions.jsp");
    }
}
