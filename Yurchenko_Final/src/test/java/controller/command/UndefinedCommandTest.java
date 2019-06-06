package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UndefinedCommandTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    UndefinedCommand undefinedCommandCommand;

    @Test
    public void shouldRedirectToErrorPage() {
        CommandResult commandResult = undefinedCommandCommand.execute(request, response);
        assertEquals(CommandPages.ERROR_PAGE, commandResult.getPage());
    }

}
