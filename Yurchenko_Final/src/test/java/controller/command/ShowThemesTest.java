package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Theme;
import model.service.ThemeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowThemesTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ThemeService themeService;
    @InjectMocks
    ShowThemes showThemes;

    @Test
    public void shouldShowThemes() {
        List<Theme> themes = new ArrayList<>(Arrays.asList(new Theme("Collections"), new Theme("Collections")));

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("radio")).thenReturn("1");
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("recordsPerPage")).thenReturn("5");
        when(themeService.findAll()).thenReturn(themes);
        when(themeService.findThemesForPagination(1, 5)).thenReturn(themes);
        doNothing().when(request).setAttribute(any(), anyString());

        CommandResult commandResult = showThemes.execute(request, response);
        assertEquals(CommandPages.ADMIN_QUESTIONS, commandResult.getPage());
    }

}
