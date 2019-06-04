package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ChangeLanguageTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;



    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void shouldChangeLanguageToRussian() {
        ChangeLanguage changeLanguageCommand = new ChangeLanguage();
        when(request.getParameter("address")).thenReturn(CommandPages.REGISTRATION_PAGE);
        when(request.getParameter("lang")).thenReturn("ru");
        when(request.getSession()).thenReturn(session);

        doNothing().when(session).setAttribute("appLocale", "ru_RU");

        CommandResult commandResult = changeLanguageCommand.execute(request, response);
        assertEquals(CommandPages.REGISTRATION_PAGE, commandResult.getPage());
        verify(request.getSession()).setAttribute("appLocale", "ru_RU");
    }

    @Test
    public void shouldChangeLanguageToEnglish() {
        ChangeLanguage changeLanguageCommand = new ChangeLanguage();
        when(request.getParameter("address")).thenReturn(CommandPages.REGISTRATION_PAGE);
        when(request.getParameter("lang")).thenReturn("eng");
        when(request.getSession()).thenReturn(session);

        doNothing().when(session).setAttribute("appLocale", "en_UK");

        CommandResult commandResult = changeLanguageCommand.execute(request, response);
        assertEquals(CommandPages.REGISTRATION_PAGE, commandResult.getPage());
        verify(request.getSession()).setAttribute("appLocale", "en_UK");
    }

}
