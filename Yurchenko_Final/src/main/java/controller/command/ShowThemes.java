package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Theme;
import model.service.ThemeService;
import model.service.factory.ServiceFactory;
import uitility.pagination.Pagination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Show list of themes in admin page.
 */
public class ShowThemes extends Command implements CommandPages {
    private ThemeService themeService;

    public ShowThemes() {
        this.themeService = ServiceFactory.getInstance().getThemeService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = setCurrentPage(req);
        int recordsPerPage = setRecordsPerPage(req);
        int rows = setRows();

        Pagination pagination = new Pagination(recordsPerPage, currentPage);

        List<Theme> themes = findThemes(pagination, recordsPerPage, rows);
        int themesSize = themes.size();

        req.getSession().setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.getSession().setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.getSession().setAttribute("themes", themes);
        req.getSession().setAttribute("themesSize", themesSize);
        req.getSession().setAttribute("act", "SHOW_BY_THEME");

        return CommandResult.forward(ADMIN_QUESTIONS);
    }

    private int setCurrentPage(HttpServletRequest req) {
        String requestCurrentPage = req.getParameter("currentPage");
        return requestCurrentPage == null ? 1 : Integer.parseInt(requestCurrentPage);
    }

    private int setRecordsPerPage(HttpServletRequest req) {
        String requestRecordsPerPage =  req.getParameter("recordsPerPage");
        return requestRecordsPerPage == null ? 5 : Integer.parseInt(requestRecordsPerPage);
    }

    private int setRows() {
        return themeService.findAll().size();
    }

    private List<Theme> findThemes(Pagination pagination, int recordsPerPage, int rows) {
        return themeService.findThemesForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)),
                recordsPerPage);
    }

}
