package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class ChangeLanguageTest {

    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    ChangeLanguage changeLanguageCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameter()
    public String language;
    @Parameterized.Parameter(1)
    public String locale;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"ru", "ru_RU"},
                {"eng", "en_UK"}
        });
    }

    @Test
    public void shouldChangeLanguageToRussian() {
        when(request.getParameter("address")).thenReturn(CommandPages.REGISTRATION_PAGE);
        when(request.getParameter("lang")).thenReturn(language);
        when(request.getSession()).thenReturn(session);

        doNothing().when(session).setAttribute("appLocale", locale);

        CommandResult commandResult = changeLanguageCommand.execute(request, response);
        assertEquals(CommandPages.REGISTRATION_PAGE, commandResult.getPage());
        verify(request.getSession()).setAttribute("appLocale", locale);
    }

}
